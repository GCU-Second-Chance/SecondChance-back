package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.KakaoLoginDto;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName : com.example.secondchanceback.controller
 * @FileName : KakaoLoginController
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@RestController
@RequestMapping("/v1/login")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    private final Logger LOGGER = LoggerFactory.getLogger(KakaoController.class);

    @PostMapping("/kakao-login")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> kakaoLogin(@RequestBody KakaoLoginDto kakaoLoginDto) {
        String code = kakaoLoginDto.getCode();
        LOGGER.info("Get Code from FrontEnd : {}", code);

        LOGGER.info("Request getAccessToken()");
        String accessToken = kakaoService.getAccessToken(code);

        if (accessToken != null) {
            LOGGER.info("Response getAccessToken()");
            LOGGER.info("access_token : {}", accessToken);
            return ResponseEntity.ok().body(accessToken);
        }
        else{
            LOGGER.info("Failed getAccessToken()");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/kakao-logout")
    public String kakaoLogout(@RequestBody KakaoLoginDto kakaoLoginDto){
        return "ok";
    }
}
