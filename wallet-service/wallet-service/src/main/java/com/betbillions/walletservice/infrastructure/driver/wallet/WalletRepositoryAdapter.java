package com.betbillions.walletservice.infrastructure.driver.wallet;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.paymenthistory.gateway.PaymentHistoryRepository;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.domain.model.wallet.gateway.WalletRepository;
import com.betbillions.walletservice.infrastructure.driver.exception.CustomException;
import com.betbillions.walletservice.infrastructure.driver.exception.TypeStateResponse;
import com.betbillions.walletservice.infrastructure.driver.helpers.ReactiveAdapterOperations;

import com.betbillions.walletservice.infrastructure.driver.wallet.mapper.WalletMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

@Repository
public class WalletRepositoryAdapter extends ReactiveAdapterOperations<Wallet, WalletEntity, Long, WalletReactiveRepository> implements WalletRepository {
    private static final String URL_MICROSERVICE = "http:localhost:8082/api/users/";
    private final PaymentHistoryRepository paymentHistoryRepository;

    public WalletRepositoryAdapter(WalletReactiveRepository repository, PaymentHistoryRepository paymentHistoryRepository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Wallet.WalletBuilder.class).build());
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    @Override
    public Mono<Page<Wallet>> getAllWallet(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(WalletMapper::userWalletEntityAUserWallet)
                .sort(Comparator.comparing(Wallet::getId))
                .collectList()
                .zipWith(repository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));

    }

    @Override
    public Mono<Wallet> addWallet(Wallet wallet) {
        return repository.findByUserId(wallet.getUserId())
                .flatMap(ele -> {
                    ele.setId(ele.getId());
                    ele.setWalletId(wallet.getWalletId());
                    return repository.save(ele)
                            .thenReturn(WalletMapper.userWalletEntityAUserWallet(ele));
                });
    }

    @Override
    public Mono<Wallet> getWalletUserId(String id) {
        return repository.findByUserId(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "La billetera no se encuentra registrada en el sistema", TypeStateResponse.Error)))
                .map(WalletMapper::userWalletEntityAUserWallet);
    }


    @Override
    public Mono<Void> createWallet(Wallet wallet) {
        return repository.save(WalletMapper.WalletAWalletEntity(wallet)).then();
    }

    @Override
    public Mono<Boolean> rechargeWallet(String userId, BigDecimal quantity, TypeHistory typeHistory) {
        return repository.findByUserId(userId).flatMap(ele -> {
            return Mono.just(true);
        });
    }

    @Override
    public Mono<Boolean> purchasingProcess(String userId, BigDecimal quantity, TypeHistory typeHistory) {
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario invalido", TypeStateResponse.Error)))
                .flatMap(ele -> {
                    BigDecimal newBalance = ele.getBalance().subtract(quantity);
                    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                        return Mono.just(Boolean.FALSE);
                    }
                    ele.setBalance(newBalance);
                    ele.setUpdatedAt(LocalDateTime.now());
                    Mono<WalletEntity> saveWallet = repository.save(ele);
                    Mono<Void> savePaymentHistory = paymentHistoryRepository.saveHistory(userId, quantity, typeHistory);
                    return Mono.when(saveWallet, savePaymentHistory)
                            .thenReturn(Boolean.TRUE);
                });
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
