package com.betbillion.bingoservice.infrastructure.driver.mongo;

import com.betbillion.bingoservice.domain.model.cardbingo.BingoBalls;
import com.betbillion.bingoservice.domain.model.cardbingo.CardBingo;
import com.betbillion.bingoservice.domain.model.cardbingo.gateway.CardBingoRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.infrastructure.driver.helper.AdapterOperations;
import com.betbillion.bingoservice.infrastructure.driver.mongo.mapper.CardBingoMapper;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.LotteryRepositoryAdapter;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CardBingoRepositoryAdapter extends AdapterOperations<CardBingo, CardBingoEntity, String, CardBingoReactiveRepository> implements CardBingoRepository {
    private final LotteryRepositoryAdapter lotteryRepositoryAdapter;
    public CardBingoRepositoryAdapter(CardBingoReactiveRepository repository, LotteryRepositoryAdapter lotteryRepositoryAdapter, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, CardBingo.CardBingoBuilder.class).build());
        this.lotteryRepositoryAdapter=lotteryRepositoryAdapter;
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
    public Mono<Response> buyCardBingo(List<CardBingo> cardBingo, String token, String lotteryId) {
        /*var data = lotteryRepositoryAdapter.priceLottery(lotteryId)
                .flatMap(ele->);*/
        return null;
    }

    public Mono<List<BingoBalls>> cardBingo() {
        return Flux.range(0, 5)
                .flatMap(this::generateBalls)
                .collectList();

    }

    public Flux<BingoBalls> generateBalls(Integer min) {
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
