package com.betbillions.walletservice.domain.model.wallet;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Wallet {
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

    public Wallet(String userId) {
        this.userId = userId;
    }
}
