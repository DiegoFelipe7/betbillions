package com.betbillion.bingoservice.domain.model.lottery;

import lombok.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class PlayersLottery {
    private String id;
    private String username;
    private String fullName;
    private String email;
}


