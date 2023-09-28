package com.betbillions.walletservice.domain.model.wallet.gateway;


import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import com.betbillions.walletservice.domain.model.wallet.Wallet;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;


public interface WalletRepository {
    Mono<Void> createWallet(Wallet wallet);
    Mono<Wallet> getWalletUser(String id);
    Mono<Void> increaseBalance(Integer userId , BigDecimal quantity , TypeHistory typeHistory);
/*

    Mono<Void> decreaseBalance(Integer userId , BigDecimal quantity, TypeHistory typeHistory);
    Mono<Void> decreaseBalanceBingoWinner(Integer userId , BigDecimal quantity, TypeHistory typeHistory);
  */

}
