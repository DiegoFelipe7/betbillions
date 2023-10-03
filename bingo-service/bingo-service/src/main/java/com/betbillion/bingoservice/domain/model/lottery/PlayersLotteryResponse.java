package com.betbillion.bingoservice.domain.model.lottery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayersLotteryResponse {
    private List<PlayersLottery> content;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private boolean first;
    private int numberOfElements;
    private boolean empty;

}
