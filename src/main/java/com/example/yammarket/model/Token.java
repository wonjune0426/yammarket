package com.example.yammarket.model;

import lombok.*;

public class Token {
    @Data
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Request {
        private String userId;
        private String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Response {
        private String token;
    }
}