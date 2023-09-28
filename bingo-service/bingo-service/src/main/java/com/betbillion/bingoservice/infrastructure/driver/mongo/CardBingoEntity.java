package com.betbillion.bingoservice.infrastructure.driver.mongo;

import com.betbillion.bingoservice.domain.model.cardbingo.BingoBalls;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@Document(value = "card_bingo")
public class CardBingoEntity {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String lotteryId;
    @Indexed
    private String roundId;
    private Integer round;
    private List<BingoBalls> card;
    private Boolean state;
    private LocalDateTime createdAt;
}
