package com.betbillions.walletservice.infrastructure.driver.wallet.mapper;

import com.betbillions.walletservice.domain.model.wallet.Wallet;
import com.betbillions.walletservice.infrastructure.driver.wallet.WalletEntity;

import java.util.UUID;

public class WalletMapper {
    private WalletMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Wallet userWalletEntityAUserWallet(WalletEntity walletEntity){
        return Wallet.builder()
                .id(walletEntity.getId())
                .uuid(walletEntity.getUuid())
                .balance(walletEntity.getBalance())
               // .userId(walletEntity.getUserId())
                .walletId(walletEntity.getWalletId())
                .currency(walletEntity.getCurrency())
                .availableBalance(walletEntity.getAvailableBalance())
                .state(walletEntity.getState())
                .createdAt(walletEntity.getCreatedAt())
                .updatedAt(walletEntity.getUpdatedAt())
                .build();
    }
    public static WalletEntity WalletAWalletEntity(Wallet wallet){
        return WalletEntity.builder()
                .id(wallet.getId())
                .uuid(UUID.randomUUID().toString())
                .balance(wallet.getBalance())
                .userId(wallet.getUserId())
                .walletId(wallet.getWalletId())
                .currency(wallet.getCurrency())
                .availableBalance(wallet.getAvailableBalance())
                .state(wallet.getState())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }
}
