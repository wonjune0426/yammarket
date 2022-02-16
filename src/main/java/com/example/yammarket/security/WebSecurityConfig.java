package com.example.yammarket.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**")
                .antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// CSRF protection 을 비활성화

        http.httpBasic();
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf() //(2)
                .disable()
                .exceptionHandling() //(3)
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement() //(4)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests() // (5)
                .antMatchers("/css/**", "js/**", "/img/**", "/lib/**", "/favicon.ico/**", "/h2-console/**").permitAll()
                .antMatchers("/user/**", "/test/**").permitAll()
                .antMatchers(HttpMethod.GET, "/posts/**").permitAll()
                .antMatchers(HttpMethod.GET, "/comments/**").permitAll()
                //.antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()//jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin()
//                .loginPage("/user/login")
// 로그인 처리 (POST /user/login)
                //.loginProcessingUrl("/user/login")
// 로그인 처리 후 성공 시 URL
                .defaultSuccessUrl("/")
// 로그인 처리 후 실패 시 URL
                .failureUrl("/user/login?error")
//                .successHandler(customSuccessHandler)
                .permitAll()
                .and()
// [로그아웃 기능]
                .logout()
// 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll();


    }

    @Bean public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://ricefriend-bucket.s3-website.ap-northeast-2.amazonaws.com/");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.validateAllowCredentials();
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source; }




}