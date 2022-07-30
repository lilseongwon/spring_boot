package com.example.springsecurity.global.property;

import com.example.springsecurity.global.security.jwt.JwtProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {JwtProperty.class})
@Configuration
public class EnableConfigurationPropertiesConfig {
}