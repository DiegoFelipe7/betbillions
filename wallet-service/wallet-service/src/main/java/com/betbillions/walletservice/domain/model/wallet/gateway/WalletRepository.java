package com.betbillions.walletservice.domain.model.wallet.gateway;


import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;


public interface WalletRepository {
    Mono<Void> createWallet(Wallet wallet);
    Mono<Wallet> getWalletUserId(String id);
    Mono<Boolean> rechargeWallet(String userId , BigDecimal quantity , TypeHistory typeHistory);
    Mono<Boolean> purchasingProcess(String userId, BigDecimal quantity , TypeHistory typeHistory);
    Mono<Page<Wallet>> getAllWallet(Pageable pageable);
    Mono<Wallet> addWallet(Wallet wallet);
/*

    Mono<Void> decreaseBalance(Integer userId , BigDecimal quantity, TypeHistory typeHistory);
    Mono<Void> decreaseBalanceBingoWinner(Integer userId , BigDecimal quantity, TypeHistory typeHistory);
  */

}
