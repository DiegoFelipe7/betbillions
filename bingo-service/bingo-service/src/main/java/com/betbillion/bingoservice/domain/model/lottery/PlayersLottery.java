package com.betbillion.bingoservice.domain.model.lottery;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PlayersLottery {
    private String id;
    private String username;
    private String fullName;
    private String email;
}
