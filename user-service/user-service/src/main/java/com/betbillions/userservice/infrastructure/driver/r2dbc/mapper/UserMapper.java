package com.betbillions.userservice.infrastructure.driver.r2dbc.mapper;

import com.betbillions.userservice.domain.model.users.References;
import com.betbillions.userservice.domain.model.users.Users;
import com.betbillions.userservice.infrastructure.driver.r2dbc.UserEntity;


public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static Users usersEntityAUsers(UserEntity usersEntity) {
        return Users.builder()
                .id(usersEntity.getId())
                .username(usersEntity.getUsername())
                .email(usersEntity.getEmail())
                .fullName(usersEntity.getFullName())
                .phone(usersEntity.getPhone())
                .country(usersEntity.getCountry())
                .city(usersEntity.getCity())
                .token(usersEntity.getToken())
                .photo(usersEntity.getPhoto())
                .refLink(usersEntity.getRefLink())
                .invitationLink(usersEntity.getInvitationLink())
                .roles(usersEntity.getRoles())
                .parentId(usersEntity.getParentId())
                .status(usersEntity.getStatus())
                .level(usersEntity.getLevel())
                .createdAt(usersEntity.getCreatedAt())
                .updatedAt(usersEntity.getUpdatedAt()).build();
    }

    public static UserEntity usersAUserEntity(Users users) {
        return UserEntity.builder()
                .id(users.getId())
                .username(users.getUsername())
                .email(users.getEmail())
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .country(users.getCountry())
                .city(users.getCity())
               .token(users.getToken())
                .photo(users.getPhoto())
                .refLink(users.getRefLink())
                .invitationLink(users.getInvitationLink())
                .roles(users.getRoles())
                .parentId(users.getParentId())
                .status(users.getStatus())
                .level(users.getLevel())
                .createdAt(users.getCreatedAt())
                .updatedAt(users.getUpdatedAt()).build();
    }

    public static References referencesDirect(UserEntity users, Integer id) {
        return References.builder()
                .id(id)
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .userName(users.getFullName())
                .dateRegistered(users.getCreatedAt())
                .build();
    }

    public static References referencesLevel(UserEntity users,Integer id) {
        return References.builder()
                .id(id)
                .fullName(users.getFullName())
                .level(users.getLevel())
                .userName(users.getFullName())
                .dateRegistered(users.getCreatedAt())
                .build();
    }
}
