package com.betbillions.walletservice.domain.model.paymenthistory;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PaymentHistory {
    private Long id;
    private BigDecimal balance ;
    private String currency;
    private String userId;
    private TypeHistory typeHistory;
    private Boolean state;
    private LocalDateTime createdAt;

    public PaymentHistory( String userId, BigDecimal balance,TypeHistory typeHistory) {
        this.userId = userId;
        this.balance = balance;
        this.typeHistory = typeHistory;
    }
}
