package com.betbillion.bingoservice.domain.model.cardbingo;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class BingoBalls {
    private String numbers;
    private Boolean state;
}
