package com.betbillion.bingoservice.infrastructure.driver.r2dbc.lottery;

import com.betbillion.bingoservice.domain.model.enums.StateLottery;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
@Table(name = "lottery")
public class LotteryEntity {
    @Id
    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime startDate;
    private Integer numberOfRounds;
    private List<String> players;
    private BigDecimal price;
    private StateLottery stateLottery;
}
