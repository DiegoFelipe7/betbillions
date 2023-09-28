package com.betbillion.authservice.domain.model.password;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Password {
    private Integer id;
    private String email;
    private String token;
    private LocalDateTime duration;
}
