package com.betbillions.walletservice.infrastructure.driver.wallet;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder(toBuilder = true)
@Table(name = "user_wallet")
public class WalletEntity {
    @Id
    private Long id;
    private String uuid;
    private BigDecimal balance;
    private BigDecimal availableBalance;
    private String userId;
    private String walletId;
    private Boolean state;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
