package com.betbillions.walletservice.infrastructure.driver.paymenthistory.mapper;


import com.betbillions.walletservice.domain.model.paymenthistory.PaymentHistory;
import com.betbillions.walletservice.infrastructure.driver.paymenthistory.PaymentHistoryEntity;

public class PaymentHistoryMapper {

    private PaymentHistoryMapper() {
        throw new IllegalStateException("Utility class");
    }
    public static PaymentHistory paymentHistoryEntityAPaymentHistory(PaymentHistoryEntity paymentHistory){
        return PaymentHistory.builder()
                .id(paymentHistory.getId())
                .balance(paymentHistory.getBalance())
                .currency(paymentHistory.getCurrency())
                .userId(paymentHistory.getUserId())
                .typeHistory(paymentHistory.getTypeHistory())
                .state(paymentHistory.getState())
                .createdAt(paymentHistory.getCreatedAt())
                .build();
    }
    public static PaymentHistoryEntity paymentHistoryAPaymentHistoryEntity(PaymentHistory paymentHistory){
        return PaymentHistoryEntity.builder()
                .id(paymentHistory.getId())
                .balance(paymentHistory.getBalance())
                .currency(paymentHistory.getCurrency())
                .userId(paymentHistory.getUserId())
                .typeHistory(paymentHistory.getTypeHistory())
                .state(paymentHistory.getState())
                .createdAt(paymentHistory.getCreatedAt())
                .build();
    }



}
