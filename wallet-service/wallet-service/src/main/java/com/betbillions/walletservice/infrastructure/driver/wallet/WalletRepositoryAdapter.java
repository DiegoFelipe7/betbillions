package com.betbillions.walletservice.infrastructure.driver.wallet;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import com.betbillions.walletservice.infrastructure.driver.exception.CustomException;
import com.betbillions.walletservice.infrastructure.driver.exception.TypeStateResponse;
import com.betbillions.walletservice.infrastructure.driver.helpers.ReactiveAdapterOperations;

import com.betbillions.walletservice.infrastructure.driver.wallet.mapper.WalletMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@Repository
public class WalletRepositoryAdapter extends ReactiveAdapterOperations<Wallet, WalletEntity, Long, WalletReactiveRepository> implements WalletRepository {

    public WalletRepositoryAdapter(WalletReactiveRepository repository , ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Wallet.WalletBuilder.class).build());

    }
    @Override
    public Mono<Void> createWallet(Wallet wallet) {
        return repository.save(WalletMapper.WalletAWalletEntity(wallet)).then();
    }

    @Override
    public Mono<Wallet> getWalletUser(String id) {
        return repository.findByUserId(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"La billetar no se encuentra registrad en el sistema", TypeStateResponse.Error)))
                .map(WalletMapper::userWalletEntityAUserWallet);
    }

    @Override
    public Mono<Void> increaseBalance(Integer userId, BigDecimal quantity, TypeHistory typeHistory) {
        return null;
    }

    /* public Mono<Response> saveWallet(String token, String wallet) {
        return null;
       return repository.findByWalletEqualsIgnoreCase(wallet)
                .switchIfEmpty(usersReactiveRepository.findByUsername(username)
                        .flatMap(data -> repository.findByUserId(data.getId())
                                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Usuario no encontrado", TypeStateResponse.Error)))
                                .flatMap(res -> {
                                    res.setWallet(wallet);
                                    return repository.save(res);
                                })
                                .thenReturn(new Response(TypeStateResponses.Success, "Billetera Registrada"))
                        )
                )
                .onErrorResume(data -> Mono.just(new Response(TypeStateResponses.Error, data.getMessage())))
                .cast(Response.class);
    }*/


/*

    @Override
    public Mono<Wallet> getWalletUser(String id) {
        return usersReactiveRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "La billetera no existe", TypeStateResponse.Error)))
                .flatMap(user -> repository.findByUserId(user.getId()).map(UserWalletMapper::userWalletEntityAUserWallet));

    }

    @Override
    public Mono<Void> increaseBalance(Integer userId, BigDecimal quantity , TypeHistory typeHistory) {
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario invalido", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    ele.setBalance(ele.getBalance().add(quantity));
                    ele.setUpdatedAt(LocalDateTime.now());
                    Mono<Void> savePaymentHistory = paymentHistoryRepository.saveHistory(userId, quantity , typeHistory);
                    Mono<WalletEntity> saveWallet = repository.save(ele);
                    return Mono.when(saveWallet,savePaymentHistory);
                }).then();
    }


    public Mono<Void> increaseBalanceWinner(Integer userId, BigDecimal quantity , TypeHistory typeHistory) {
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario invalido", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    ele.setBingoWinnings(ele.getBingoWinnings().add(quantity));
                    ele.setUpdatedAt(LocalDateTime.now());
                    Mono<Void> savePaymentHistory = paymentHistoryRepository.saveHistory(userId, quantity , typeHistory);
                    Mono<WalletEntity> saveWallet = repository.save(ele);
                    return Mono.when(saveWallet,savePaymentHistory);
                }).then();
    }

    @Override
    public Mono<Void> decreaseBalance(Integer userId, BigDecimal quantity , TypeHistory typeHistory) {
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario invalido", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    BigDecimal newBalance = ele.getBalance().subtract(quantity);
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        return decreaseBalanceBingoWinner(userId,quantity,typeHistory);
                    }
                    ele.setBalance(newBalance);
                    ele.setUpdatedAt(LocalDateTime.now());
                    Mono<WalletEntity> saveWallet = repository.save(ele);
                    Mono<Void> savePaymentHistory = paymentHistoryRepository.saveHistory(userId, quantity , TypeHistory.Shopping);
                    return Mono.when(saveWallet,savePaymentHistory);
                }).then();
    }
    @Override
    public Mono<Void> decreaseBalanceBingoWinner(Integer userId, BigDecimal quantity , TypeHistory typeHistory ) {
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario invalido", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    BigDecimal newBalance = ele.getBingoWinnings().subtract(quantity);
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "El saldo es insuficciente", TypeStateResponse.Error));
                    }
                    ele.setBingoWinnings(newBalance);
                    ele.setUpdatedAt(LocalDateTime.now());
                    Mono<WalletEntity> saveWallet = repository.save(ele);
                    Mono<Void> savePaymentHistory = paymentHistoryRepository.saveHistory(userId, quantity , typeHistory);
                    return Mono.when(saveWallet,savePaymentHistory);
                }).then();
    }*/



}
