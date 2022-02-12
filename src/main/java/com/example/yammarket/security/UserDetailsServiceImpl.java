package com.example.yammarket.security;

import com.example.yammarket.model.Users;
import com.example.yammarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        Users user = userRepository.findByUser_id(user_id)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + user_id));

        return new UserDetailsImpl(user);
    }
}