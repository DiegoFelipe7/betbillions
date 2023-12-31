package com.betbillions.userservice.domain.model.users;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class Users {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String country;
    private String city;
    private String token;
    private String photo;
    private String refLink;
    private String invitationLink;
    private String  roles;
    private String parentId;
    private Boolean status;
    private Boolean commission;
    private Integer level;
    private Integer position;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
