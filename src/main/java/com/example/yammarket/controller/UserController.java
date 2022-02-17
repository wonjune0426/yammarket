package com.example.yammarket.controller;

import com.example.yammarket.dto.CheckIdRequestDto;
import com.example.yammarket.dto.SignupRequestDto;
import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import com.example.yammarket.security.*;

import com.example.yammarket.model.Token;
import com.example.yammarket.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController{

    private JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;


    @PostMapping(
            value ="/user/login",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Token.Response login(
            final HttpServletRequest req,
            final HttpServletResponse res,
            @Valid @RequestBody Token.Request request) throws Exception {

        Users users = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("없는 사용자입니다."));

        boolean checkPassword = passwordEncoder.matches(request.getPassword(), users.getPassword());

        if(!checkPassword){
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

    @PostMapping("/user/idCheck")
    public Boolean checkId(@RequestBody CheckIdRequestDto requestDto) {
        Boolean result = userService.checkId(requestDto);
        return result;
    }


    @PostMapping("/user/check")
    public Users checkUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader("authorization");
        String userId = JwtTokenProvider.getUserIdFromJWT(token);
        Users user = userRepository.findByUserId(userId).orElseThrow(()->new Exception("invalid Token"));

        return user;
    }


}

