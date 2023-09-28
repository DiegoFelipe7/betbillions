package com.betbillions.walletservice.domain.model.wallet;

import com.betbillions.walletservice.domain.model.user.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class WalletUser {
    private Wallet wallet;
    private User user;
}
