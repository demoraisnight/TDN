package com.vinicius.cinema.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfigV1 extends WebSecurityConfigurerAdapter {
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Bean
//    JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//        tokenConverter.setSigningKey(jwtSecret);
//        return tokenConverter;
//    }
//
//    @Bean
//    JwtTokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//}