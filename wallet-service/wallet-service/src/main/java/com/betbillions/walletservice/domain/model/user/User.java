package com.betbillions.walletservice.domain.model.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class User {
    private String username;
    private String email;
    private String fullName;
    private String photo;
    private String phone;
    private String country;
    private String city;

}
