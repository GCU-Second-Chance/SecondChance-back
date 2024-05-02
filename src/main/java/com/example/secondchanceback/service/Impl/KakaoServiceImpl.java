package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.KakaoDto;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
public class KakaoServiceImpl implements KakaoService {

    private String baseUrl;
    private String path;
    @Value("${kakao.request.client_id}")
    private String client_id;
    @Value("${kakao.redirect_uri}")
    String redirect_uri;
    @Value("${kakao.client_secret}")
    String client_secret;

    @Override
    public String getAccessToken(String code) {
        String accessToken;
        KakaoDto kakaoDto;
        String refreshToken;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity<String> entity = new HttpEntity<>("", headers);
            baseUrl = "https://kauth.kakao.com";
            path = "/oauth/token";

            URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", client_id)
                .queryParam("redirect_uri", redirect_uri)
                    .queryParam("code", code)
                    .queryParam("client_secret", client_secret)
                .encode().build().toUri();

            MultiValueMap<String,String> body = new LinkedMultiValueMap<>();

            System.out.println(uri);
            System.out.println(entity);
            System.out.println("카카오에 AccessToken 요청");
            RestTemplate restTemplate = new RestTemplate();
            kakaoDto = restTemplate.postForObject(uri, headers, KakaoDto.class);

            if(kakaoDto.getAccess_token() != null){
                System.out.println("성공적으로 토큰 받기 성공!");
                accessToken = kakaoDto.getAccess_token();
                return accessToken;
            }else{
                System.out.println("토큰 받기 실패!");
                return null;
            }
        }
        catch (Exception e){
            System.out.println("요청 실패");
            return null;
        }
    }

    @Override
    public String getUserInfo(String accessToken) {
        baseUrl = "https://kapi.kakao.com";
        path = "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", " Bearer " + accessToken);
        return "ok";
    }

    @Override
    public String logout(String accessToken) {
        return null;
    }
}
