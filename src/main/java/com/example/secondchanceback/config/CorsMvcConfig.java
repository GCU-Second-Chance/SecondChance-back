package com.example.secondchanceback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @PackageName : com.example.second_chance_back.config
 * @FileName : CorsMvcConfig
 * @Author : noglass_gongdae
 * @Date : 2024-05-10
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Value("${frontend.address}")
    private String address;
    // Cors 전역 설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .exposedHeaders("Set-Cookie")
            .allowedOrigins(address);
    }
}
