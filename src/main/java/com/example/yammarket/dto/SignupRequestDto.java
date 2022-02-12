package com.example.yammarket.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String user_id;
    private String nickname;
    private String password;

    public SignupRequestDto(String user_id, String nickname, String password) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.password = password;
    }
}