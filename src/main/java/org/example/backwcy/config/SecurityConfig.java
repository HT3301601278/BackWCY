package org.example.backwcy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.CorsFilter;  // 添加这行

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll();  // 允许所有请求
        return http.build();
    }
}