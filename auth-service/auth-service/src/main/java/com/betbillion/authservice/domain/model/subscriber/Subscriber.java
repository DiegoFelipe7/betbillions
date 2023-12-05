package com.betbillion.authservice.domain.model.subscriber;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Subscriber {
    private String userId;
}
