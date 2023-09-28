package com.betbillion.bingoservice.infrastructure.driver.r2dbc.round;

import com.betbillion.bingoservice.domain.model.enums.StateRound;
import com.betbillion.bingoservice.domain.model.round.Round;
import com.betbillion.bingoservice.domain.model.round.gateway.RoundRepository;
import com.betbillion.bingoservice.infrastructure.driver.helper.ReactiveAdapterOperations;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery.LotteryReactiveRepository;
import com.betbillion.bingoservice.infrastructure.driver.r2dbc.round.mapper.RoundMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class RoundRepositoryAdapter extends ReactiveAdapterOperations<Round, RoundEntity, Long, RoundReactiveRepository> implements RoundRepository {
    private final LotteryReactiveRepository lotteryRepository;

    public RoundRepositoryAdapter(RoundReactiveRepository repository, LotteryReactiveRepository lotteryRepository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Round.RoundBuilder.class).build());
        this.lotteryRepository = lotteryRepository;
    }

    @Override
    public Mono<Void> saveRounds(List<Round> rounds, String lotteryId) {
        return Flux.fromIterable(rounds)
                .index()
                .concatMap(tuple -> {
                    Integer number = tuple.getT1().intValue() + 1;
                    Round round = tuple.getT2();
                    round.setNumberRound(number);
                    round.setIdLottery(lotteryId);
                    round.setStateRound(StateRound.WAITING);
                    return repository.save(RoundMapper.roundEntity(round));
                }).then();
    }

    @Override
    public Flux<Round> getAllRoundsLottery(String id) {
        return repository.findAll().filter(ele -> ele.getIdLottery().equals(id))
                .filter(ele -> ele.getStateRound().equals(StateRound.WAITING))
                .map(RoundMapper::roundEntityARound);
    }

    @Override
    public Mono<Void> inactiveRounds(String id) {
        return repository.findAll()
                .filter(ele -> ele.getIdLottery().equals(id)).log()
                .flatMap(ele -> {
                    ele.setStateRound(StateRound.CANCELED);
                    ele.setCompleted(Boolean.TRUE);
                    return repository.save(ele);
                }).then();
    }
}
