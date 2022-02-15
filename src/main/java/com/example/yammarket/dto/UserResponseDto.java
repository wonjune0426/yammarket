package com.example.yammarket.dto;


import com.example.yammarket.model.Users;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private String userId;
    private String nickName;

    public UserResponseDto(Users users) {
        this.userId = users.getUserId();
        this.nickName = users.getNickname();

    }

}