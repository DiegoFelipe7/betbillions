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
    private Integer userId;
    private TypeHistory typeHistory;
    private Boolean state;
    private LocalDateTime createdAt;
}
