package com.example.gatewayservice.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Slf4j
public class GatewayConfiguration {
    private final GatewayUrlsProps urlsProps;

    @Autowired
    public GatewayConfiguration(GatewayUrlsProps urlsProps) {
        this.urlsProps = urlsProps;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public void urls(){
    log.info("Gateway properties - {}", urlsProps);
    }
}
