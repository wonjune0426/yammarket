package com.example.yammarket.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String userId;
    private String nickname;
    private String password;

}