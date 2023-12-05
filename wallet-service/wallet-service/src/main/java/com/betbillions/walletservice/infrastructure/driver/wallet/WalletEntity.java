package com.betbillions.walletservice.infrastructure.driver.wallet;

import com.betbillions.walletservice.infrastructure.driver.exception.CustomException;
import com.betbillions.walletservice.infrastructure.driver.exception.TypeStateResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

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

    public BigDecimal balance(BigDecimal amount)  throws CustomException{
        BigDecimal total =this.balance.subtract(amount);
        if(total.compareTo(BigDecimal.ZERO)<0){
            throw new CustomException(HttpStatus.BAD_REQUEST,"",TypeStateResponse.Error);
        }
        return total;
    }



}
