package com.example.yammarket.security;
//test
import lombok.extern.slf4j.Slf4j;
//import com.spring.guide.global.error.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException e) throws IOException {

        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

//        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");
//
//        request.setAttribute("response.failure.code", unAuthorizationCode.name());
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, unAuthorizationCode.message());

//        ErrorCode unAuthorizationCode = (ErrorCode) request.getAttribute("unauthorization.code");

//        request.setAttribute("response.failure.code", request.getAttribute("unauthorization.code"));
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, (String)request.getAttribute("unauthorization.code"));
    }
}