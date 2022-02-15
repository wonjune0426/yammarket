package com.example.yammarket.controller;

import com.example.yammarket.dto.SignupRequestDto;
import com.example.yammarket.dto.UserResponseDto;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import com.example.yammarket.security.*;

import com.example.yammarket.model.Token;
import com.example.yammarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController{


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PostMapping(
            value ="/user/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Token.Response login(
            final HttpServletRequest req,
            final HttpServletResponse res,
            @Valid @RequestBody Token.Request request) throws Exception {

        Users users = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다."));


        if(users.getPassword().equals(request.getSecret())){//.equals(users.getPassword())){
            throw new IllegalArgumentException("비밀번호를 확인하세요.");
        }

        Authentication authentication = new UserAuthentication(request.getUserId(), null, null);
        String token = JwtTokenProvider.generateToken(authentication);

        Token.Response response = Token.Response.builder().token(token).build();

        return response;
    }



    @PostMapping("/user/signup")
    public Boolean registerUser(@RequestBody SignupRequestDto requestDto) {
        Boolean result = userService.registerUser(requestDto);
        return result;
    }


}

