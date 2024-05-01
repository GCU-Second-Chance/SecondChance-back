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
        String accessToken;
        KakaoDto kakaoDto;
        String refreshToken;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>("", headers);

            URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/oauth/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", redirect_uri)
                .queryParam("code", code)
                .queryParam("client_secret", client_secret)
                .encode().build().toUri();

            RestTemplate restTemplate = new RestTemplate();
            kakaoDto = restTemplate.postForObject(uri, entity, KakaoDto.class);

            if(kakaoDto.getAccessToken() != null){
                System.out.println("성공적으로 토큰 받기 성공!");
                accessToken = kakaoDto.getAccessToken();
                return accessToken;
            }else{
                System.out.println("토큰 받기 실패!");
                return null;
            }
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
