package com.betbillion.authservice.domain.model.utils;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Response {
    private TypeStateResponses typeStatus;
    private String message;
}
