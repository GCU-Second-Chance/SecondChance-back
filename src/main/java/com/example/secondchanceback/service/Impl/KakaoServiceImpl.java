package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.KakaoResponseLoginDto;
import com.example.secondchanceback.dto.KakaoUserInfoDto;
import com.example.secondchanceback.service.KakaoService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    private Logger LOGGER = LoggerFactory.getLogger(KakaoServiceImpl.class);
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
        KakaoResponseLoginDto kakaoResponseLoginDto;
        String refreshToken;
        Map<String, Boolean> map = new HashMap<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

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

            LOGGER.info("Request AccessToken URI to Kakao : {}", uri);

            LOGGER.info("Request AccessToken to Kakao");
            RestTemplate restTemplate = new RestTemplate();
            kakaoResponseLoginDto = restTemplate.postForObject(uri, headers, KakaoResponseLoginDto.class);

            if(kakaoResponseLoginDto.getAccess_token() != null){
                LOGGER.info("Response AccessToken from Kakao");
                accessToken = kakaoResponseLoginDto.getAccess_token();
                return accessToken;
            }else{
                LOGGER.info("Failed Response AccessToken from Kakao");
                return null;
            }
        }
        catch (Exception e){
            LOGGER.info("Failed Request to Kakao");
            return null;
        }
    }

    @Override
    public String getUserInfo(String accessToken) {
        try{
            baseUrl = "https://kapi.kakao.com/v2/user/me";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + accessToken);

            HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);

            URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .encode().build().toUri();
            LOGGER.info("Request UserInfo URI to Kakao : {}", uri);

            LOGGER.info("Request UserInfo to Kakao");
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, httpEntity, String.class);

            System.out.println(responseEntity);
            return "ok";
        }catch (Exception e) {
            LOGGER.info("Failed Request UserInfo");
            return null;
        }
    }

    @Override
    public String logout(String accessToken) {
        return null;
    }
}
