package com.example.springsecurity.global.property;

import com.example.springsecurity.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {JwtProperties.class})
@Configuration
public class EnableConfigurationPropertiesConfig {
}