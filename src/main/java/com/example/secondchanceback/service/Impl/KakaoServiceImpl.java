package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.KakaoLoginDto;
import com.example.secondchanceback.dto.KakaoUserInfoDto;
import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

    private final UserRepository userRepository;
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
    public KakaoLoginDto getAccessToken(String code) {
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
            ResponseEntity<KakaoLoginDto> responseEntity = restTemplate.postForEntity(uri, headers, KakaoLoginDto.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                LOGGER.info("Response AccessToken from Kakao");
                return responseEntity.getBody();
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
    public UserDto getUserInfo(String accessToken) {
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
            ResponseEntity<KakaoUserInfoDto> responseEntity = restTemplate.postForEntity(uri, httpEntity, KakaoUserInfoDto.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                LOGGER.info("Response UserInfo from Kakao");
                UserEntity userEntity = new UserEntity(responseEntity.getBody().getId(),
                    responseEntity.getBody().getProperties().get("nickname"), "");
                UserDto userDto = new UserDto(userEntity.getId(), userEntity.getNickname());
                if(userRepository.existsById(userEntity.getId())){
                    LOGGER.info("already member");
                }
                else{
                    userRepository.save(userEntity);
                    LOGGER.info("save member");
                }
                return userDto;
            }
            else{
                LOGGER.info("Failed Response UserInfo from Kakao, statusCode : {}", responseEntity.getStatusCode());
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String logout(String accessToken) {
        return null;
    }
}
