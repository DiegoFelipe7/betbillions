package com.betbillion.authservice.infrastructure.drivers.auth.mapper;

import com.betbillion.authservice.domain.model.auth.Users;
import com.betbillion.authservice.infrastructure.drivers.auth.UsersEntity;
import com.betbillion.authservice.infrastructure.drivers.helpers.Utils;

public class AuthMapper {
    private AuthMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Users usersEntityAUsers(UsersEntity usersEntity){
        return Users.builder()
                .id(usersEntity.getId())
                .username(usersEntity.getUsername())
                .email(usersEntity.getEmail())
                .password(usersEntity.getPassword())
                .fullName(usersEntity.getFullName())
                .phone(usersEntity.getPhone())
                .country(usersEntity.getCountry())
                .city(usersEntity.getCity())
                .emailVerified(usersEntity.getEmailVerified())
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

    public static UsersEntity usersAUserEntity(Users users){
        return UsersEntity.builder()
                .id(Utils.uid())
                .username(users.getUsername())
                .email(users.getEmail())
                .password(users.getPassword())
                .fullName(users.getFullName())
                .phone(users.getPhone())
                .country(users.getCountry())
                .city(users.getCity())
                .emailVerified(users.getEmailVerified())
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
}
