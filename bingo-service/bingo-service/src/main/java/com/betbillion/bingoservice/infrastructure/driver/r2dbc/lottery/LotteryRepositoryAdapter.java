package com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.PlayersLotteryResponse;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import com.betbillion.bingoservice.domain.model.round.gateway.RoundRepository;
import com.betbillion.bingoservice.domain.model.utils.Pagination;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.model.utils.ResponseTrelloDTO;
import com.betbillion.bingoservice.domain.model.utils.TypeStateResponses;
import com.betbillion.bingoservice.infrastructure.driver.exception.CustomException;
import com.betbillion.bingoservice.infrastructure.driver.exception.TypeStateResponse;
import com.betbillion.bingoservice.infrastructure.driver.helper.ReactiveAdapterOperations;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.mapper.LotteryMapper;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.round.RoundRepositoryAdapter;
import com.betbillion.bingoservice.infrastructure.driver.utils.Pagination2;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;


@Repository
public class LotteryRepositoryAdapter extends ReactiveAdapterOperations<Lottery, LotteryEntity, Long, LotteryReactiveRepository> implements LotteryRepository {
    private final RoundRepository roundRepository;


    public LotteryRepositoryAdapter(LotteryReactiveRepository repository, RoundRepositoryAdapter roundRepository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Lottery.LotteryBuilder.class).build());
        this.roundRepository = roundRepository;

    }

    @Override
    public Mono<Response> saveLottery(LotteryDto lotteryDto) {
        lotteryDto.setStateLottery(StateLottery.PENDING);
        return repository.save(LotteryMapper.lotteryDtoALotteryEntity(lotteryDto))
                .flatMap(ele -> roundRepository.saveRounds(lotteryDto.getRounds(), ele.getUuid()))
                .thenReturn(new Response(TypeStateResponses.Success, "Sorteo creado exitosamente!"));
    }


    @Override
    public Mono<Page<Lottery>> getAllLottery(Pageable pageable) {
        return repository.findAllBy(pageable)
                .filter(ele -> ele.getStateLottery().equals(StateLottery.PENDING))
                .map(LotteryMapper::lotteryEntityALottery)
                .collectList()
                .zipWith(repository.countByStateLottery(StateLottery.PENDING.name()))
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<LotteryDto> getLotteryId(String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "El Juego no existe", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    if (ele.getStateLottery().equals(StateLottery.PENDING)) {
                        return Mono.just(LotteryMapper.lotteryDto(ele, List.of()));
                    }
                    return roundRepository.getAllRoundsLottery(ele.getUuid())
                            .collectList()
                            .map(rounds -> LotteryMapper.lotteryDto(ele, rounds));
                });
    }

    @Override
    public Mono<Response> inactivateLottery(String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Loteria invalida", TypeStateResponse.Success)))
                .flatMap(ele -> {
                    ele.setId(ele.getId());
                    ele.setStateLottery(StateLottery.FINALIZED);
                    return repository.save(ele)
                            .then(roundRepository.inactiveRounds(id))
                            .thenReturn(new Response(TypeStateResponses.Success, "Loteria inactivada"));
                });
    }

    @Override
    public Mono<BigDecimal> priceLottery(String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "El id de la loteria es invalida", TypeStateResponse.Success)))
                .map(LotteryEntity::getPrice);
    }


    @Override
    public Mono<PlayersLotteryResponse> getAllPlayers(Pageable pageable, String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Id de la loteria invalida", TypeStateResponse.Error)))
                .map(LotteryEntity::getPlayers)
                .flatMap(ele ->
                        WebClient.create().get()
                                .uri("http://localhost:8081/api/users/players", uriBuilder -> uriBuilder
                                        .queryParam("playersId", ele)
                                        .queryParam("page", pageable.getPageNumber())
                                        .queryParam("size", pageable.getPageSize())
                                        .build())
                                .retrieve()
                                .bodyToMono(PlayersLotteryResponse.class));
    }

    @Override
    public Mono<LotteryDto> updateStateLottery(String uuid, StateLottery stateLottery) {
        return repository.findByUuid(uuid)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "El id de la lotería no existe", TypeStateResponse.Error)))
                .flatMap(lottery -> {
                    lottery.setStateLottery(stateLottery);
                    return repository.save(lottery);
                })
                .flatMap(savedLottery -> getLotteryId(savedLottery.getUuid()));
    }

    @Override
    public Mono<Void> addPlayerLottery(String lotteryId, String userId) {
        return repository.findByUuid(lotteryId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "La loteria no existe", TypeStateResponse.Warning)))
                .flatMap(ele -> {
                    ele.setId(ele.getId());
                    ele.getPlayers().add(userId);
                    return repository.save(ele);
                }).then();
    }

    @Override
    public Mono<ResponseTrelloDTO<List<Lottery>>> prueba(Pagination pagination) {
        return repository.findAll()
                .map(LotteryMapper::lotteryEntityALottery)
                .skip(pagination.getOffset())
                .take(pagination.getLimit())
                .collectList()
                .zipWith(repository.count())
                .map(ele ->new ResponseTrelloDTO<List<Lottery>>(ele.getT1(), pagination.pagination(ele.getT2())));

    }


}
