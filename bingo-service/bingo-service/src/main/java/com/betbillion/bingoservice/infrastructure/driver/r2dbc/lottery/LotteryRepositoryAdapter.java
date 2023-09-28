package com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import com.betbillion.bingoservice.domain.model.lottery.Lottery;
import com.betbillion.bingoservice.domain.model.lottery.LotteryDto;
import com.betbillion.bingoservice.domain.model.lottery.gateway.LotteryRepository;
import com.betbillion.bingoservice.domain.model.round.gateway.RoundRepository;
import com.betbillion.bingoservice.domain.model.utils.Response;
import com.betbillion.bingoservice.domain.model.utils.TypeStateResponses;
import com.betbillion.bingoservice.infrastructure.driver.exception.CustomException;
import com.betbillion.bingoservice.infrastructure.driver.exception.TypeStateResponse;
import com.betbillion.bingoservice.infrastructure.driver.helper.ReactiveAdapterOperations;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.mapper.LotteryMapper;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.round.RoundRepositoryAdapter;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Comparator;


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
    public Flux<Lottery> getAllLottery() {
        return  repository.findAll()
                .filter(ele->ele.getStateLottery().equals(StateLottery.PENDING))
                .map(LotteryMapper::lotteryEntityALottery)
                .sort(Comparator.comparing(Lottery::getId));
    }

    @Override
    public Mono<LotteryDto> getLotteryId(String id) {
       return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "El Juego no existe", TypeStateResponse.Error)))
                .flatMap(ele -> roundRepository.getAllRoundsLottery(ele.getUuid())
                        .collectList()
                        .map(res -> LotteryMapper.lotteryDto(ele, res)));
    }


    @Override
    public Mono<Response> inactivateLottery(String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"Loteria invalida",TypeStateResponse.Success)))
                .flatMap(ele->{
                    ele.setId(ele.getId());
                    ele.setStateLottery(StateLottery.FINALIZED);
                    return repository.save(ele)
                            .then(roundRepository.inactiveRounds(id))
                            .thenReturn(new Response(TypeStateResponses.Success,"Loteria inactivada"));
                });
    }

    @Override
    public Mono<BigDecimal> priceLottery(String id) {
        return repository.findByUuid(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"El id de la loteria es invalida",TypeStateResponse.Success)))
                .map(LotteryEntity::getPrice);
    }


}
