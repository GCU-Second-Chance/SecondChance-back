package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.KakaoDto;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

/**
 * @PackageName : com.example.secondchanceback.service.Impl
 * @FileName : KakaoServiceImpl
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

    @Value("{$kakao.request.getAccessToken.url}")
    private String baseUrl;
    @Value("{$kakao.request.client_id}")
    private String client_id;
    @Value("{$kakao.redirect_uri")
    String redirect_uri;
    @Value("{$kakao.client_secret")
    String client_secret;

    @Override
    public String getAccessToken(String code) {
        String refreshToken;
        try {
            System.out.println("카카오에 액세스 토큰 요청");
            Mono<KakaoDto> mono = WebClient.builder()
                    .baseUrl(baseUrl)
                    .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build()
                    .post() //어떤 요청인가 .
                    .uri(uriBuilder -> uriBuilder
                            .path("/oauth/token")
                            .queryParam("grant_type", "authorization_code")
                            .queryParam("client_id", client_id)
                            .queryParam("redirect_uri", redirect_uri)
                            .queryParam("code", code)
                            .queryParam("client_secret", client_secret)
                            .build()) //.toUri() 는 안써도 됨
                    .retrieve()
                    .bodyToMono(KakaoDto.class);
            KakaoDto kakaoDto = mono.block();
            System.out.println("카카오로부터 액세스 토큰을 받음");
            String accessToken = kakaoDto.getAccessToken();
            System.out.println(accessToken);
            return accessToken;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public String getUserInfo(String accessToken) {
        return "ok";
    }

    @Override
    public String logout(String accessToken) {
        return null;
    }
}
