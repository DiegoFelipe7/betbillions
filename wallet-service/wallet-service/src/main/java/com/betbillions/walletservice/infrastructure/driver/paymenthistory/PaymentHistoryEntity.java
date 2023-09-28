package com.betbillions.walletservice.infrastructure.driver.paymenthistory;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payment_history")
public class PaymentHistoryEntity {
    private Integer id;
    private BigDecimal balance ;
    private String currency;
    private Integer userId;
    private TypeHistory typeHistory;
    private Boolean state;
    private LocalDateTime createdAt;
}
