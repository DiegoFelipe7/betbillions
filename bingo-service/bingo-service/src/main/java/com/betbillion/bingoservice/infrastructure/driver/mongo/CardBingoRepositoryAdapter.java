package com.betbillion.bingoservice.infrastructure.driver.mongo;

import com.betbillion.bingoservice.domain.model.cardbingo.BingoBalls;
import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.cardbingo.gateway.CardBingoRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.model.utils.TypeStateResponses;
import com.betbillion.bingoservice.infrastructure.driver.exception.CustomException;
import com.betbillion.bingoservice.infrastructure.driver.exception.TypeStateResponse;
import com.betbillion.bingoservice.infrastructure.driver.helper.AdapterOperations;
import com.betbillion.bingoservice.infrastructure.driver.mongo.mapper.CardBingoMapper;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.LotteryRepositoryAdapter;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class CardBingoRepositoryAdapter extends AdapterOperations<CardBingo, CardBingoEntity, String, CardBingoReactiveRepository> implements CardBingoRepository {
    private final LotteryRepositoryAdapter lotteryRepositoryAdapter;

    public CardBingoRepositoryAdapter(CardBingoReactiveRepository repository, LotteryRepositoryAdapter lotteryRepositoryAdapter, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CardBingo.CardBingoBuilder.class).build());
        this.lotteryRepositoryAdapter = lotteryRepositoryAdapter;
    }

    @Override
    public Flux<CardBingo> generateCardBingo() {
        return Flux.range(0, 10)
                .flatMap(ele -> cardBingo()
                        .map(CardBingo::new));

    }

    @Override
    public Flux<CardBingo> bingoCardUsers(String lotteryId, String userId) {
        return repository.findAll()
                .filter(ele -> ele.getLotteryId().equals(lotteryId))
                .filter(data -> data.getUserId().equals(userId))
                .map(CardBingoMapper::cardBingoEntityACardBingo);
    }

    @Override
    public Mono<Response> buyCardBingo(List<CardBingo> cardBingo, String userId, String lotteryId) {
        return lotteryRepositoryAdapter.priceLottery(lotteryId)
                .flatMap(price -> validatePurchase(userId, price.multiply(BigDecimal.valueOf(cardBingo.size())))
                        .then(saveCartBingo(cardBingo, userId, lotteryId)))
                .thenReturn(new Response(TypeStateResponses.Success, "Compra exitosa"));
    }

    private Mono<Boolean> validatePurchase(String userId, BigDecimal amount) {
        return WebClient.create()
                .patch()
                .uri("http://localhost:8081/api/wallet/shopping")
                .bodyValue(Map.of("userId", userId, "balance", amount))
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(throwable -> Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Ocurrio un error, recarga la pagina e intentalo nuevamente", TypeStateResponse.Error)))
                .flatMap(success -> {
                    if (Boolean.TRUE.equals(success)) {
                        return Mono.just(Boolean.TRUE);
                    }
                    return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Saldo insuficiente en la billetera", TypeStateResponse.Warning));
                });
    }

    private Mono<Void> saveCartBingo(List<CardBingo> products, String userId, String lotteryId) {
        return Flux.fromIterable(products)
                .index()
                .concatMap(card -> {
                    Integer number = card.getT1().intValue() + 1;
                    card.getT2().setUserId(userId);
                    card.getT2().setLotteryId(lotteryId);
                    card.getT2().setRound(number);
                    return repository.save(CardBingoMapper.cardBingoACardBingoEntity(card.getT2()));
                }).then(lotteryRepositoryAdapter.addPlayerLottery(lotteryId,userId));
    }

    @Override
    public Mono<CardBingo> getCardBingoRound(String lottery, String roundId, String userId) {
        return repository.findByLotteryIdAndRoundIdAndUserId(lottery, roundId, userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "No tienes cartones de bingo para esta ronda", TypeStateResponse.Warning)))
                .flatMap(ele -> {
                    if (ele.getState().equals(Boolean.FALSE)) {
                        return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "No tienes cartones activos", TypeStateResponse.Warning));
                    }
                    return Mono.just(CardBingoMapper.cardBingoEntityACardBingo(ele));
                });
    }


    private Mono<List<BingoBalls>> cardBingo() {
        return Flux.range(0, 5)
                .flatMap(this::generateBalls)
                .collectList();

    }

    private Flux<BingoBalls> generateBalls(Integer min) {
        char[] letters = {'B', 'I', 'N', 'G', 'O'};
        Set<Integer> generatedNumbers = new HashSet<>();
        return Flux.range(1, 5)
                .map(index -> {
                    int value;
                    do {
                        value = (int) (Math.random() * 15) + 1 + 15 * min;
                    } while (generatedNumbers.contains(value));
                    generatedNumbers.add(value);
                    String ballNumber = String.valueOf(letters[min]) + value;
                    return new BingoBalls(ballNumber, false);
                });
    }
}
