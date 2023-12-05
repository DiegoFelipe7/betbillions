package com.betbillions.walletservice.infrastructure.driver.paymenthistory;

import com.betbillions.walletservice.domain.model.enums.TypeHistory;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "payment_history")
@Builder(toBuilder = true)
public class PaymentHistoryEntity {
    @Id
    private Long id;
    private BigDecimal balance ;
    private String currency;
    private String userId;
    private TypeHistory typeHistory;
    private Boolean state;
    private LocalDateTime createdAt;
}
