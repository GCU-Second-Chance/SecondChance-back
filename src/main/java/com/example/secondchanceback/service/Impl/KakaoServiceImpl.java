package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.KakaoResponseLoginDto;
import com.example.secondchanceback.dto.KakaoUserInfoDto;
import com.example.secondchanceback.service.KakaoService;
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
            baseUrl = "https://kapi.kakao.com";
            path = "/v2/user/me";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + accessToken);
            httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .encode().build().toUri();

            LOGGER.info("Request UserInfo URI to Kakao : {}", uri);

            LOGGER.info("Request UserInfo to Kakao");
            RestTemplate restTemplate = new RestTemplate();
            KakaoUserInfoDto kakaoUserInfoDto= restTemplate.postForObject(uri, httpHeaders, KakaoUserInfoDto.class);

            if(kakaoUserInfoDto.getId() != null){
                LOGGER.info("Response UserInfo from Kakao : {} ", kakaoUserInfoDto);
                return "ok";
            }else{
                LOGGER.info("Failed Response UserInfo from Kakao");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String logout(String accessToken) {
        return null;
    }
}
