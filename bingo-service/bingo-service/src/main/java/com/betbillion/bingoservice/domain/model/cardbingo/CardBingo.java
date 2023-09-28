package com.betbillion.bingoservice.domain.model.cardbingo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class CardBingo {
    private String id;
    private String userId;
    private String lotteryId;
    private String roundId;
    private Integer round;
    private List<BingoBalls> card;
    private Boolean state;
    private LocalDateTime createdAt;

    public CardBingo(List<BingoBalls> card) {
        this.card = card;
    }
}
