package com.example.yammarket.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    public UserAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public UserAuthentication(String principal, String credentials,
                              List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}