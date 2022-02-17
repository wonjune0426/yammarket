package com.example.yammarket.security;


import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
public class TokenUser {
    private final UserRepository userRepository;
    public Users getUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("authorization");
        String userId = JwtTokenProvider.getUserIdFromJWT(token);
        return userRepository.findByUserId(userId).orElseThrow(()->new Exception("invalid Token"));
    }
}
