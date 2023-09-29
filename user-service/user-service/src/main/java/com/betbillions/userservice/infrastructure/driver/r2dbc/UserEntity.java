package com.betbillions.userservice.infrastructure.driver.r2dbc;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
