package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.KakaoLoginDto;
import com.example.secondchanceback.dto.KakaoUserInfoDto;
import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.service.KakaoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
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
@CrossOrigin(origins = "http://localhost:5173")
public class KakaoController {

    private final KakaoService kakaoService;

    private final Logger LOGGER = LoggerFactory.getLogger(KakaoController.class);

    @GetMapping("/kakao-login")
    public ResponseEntity<UserDto> kakaoLogin(@RequestParam("code") String code) {
        LOGGER.info("Get Code from FrontEnd : {}", code);

        LOGGER.info("Request getAccessToken()");
        KakaoLoginDto kakaoLoginDto = kakaoService.getAccessToken(code);
        String accessToken = kakaoLoginDto.getAccess_token();
        LOGGER.info("access_token : {}", accessToken);

        if(accessToken != null){
            UserDto userDto = kakaoService.getUserInfo(accessToken);
            return ResponseEntity.ok(userDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // accessToken이 null임에도 getUserInfo를 부름.
        // 안부르게 끔 위의 방법을 포함하여
        // 1. map에서 true, false를 사용하여 해봄
        // 2. getAccessToken을 map객체로 반환하게끔 하여 accessToken이 있으면 true, 없으면 false로 하여 isEmpty 함수로 체크하여 부름
        // 위의 두 방법 전부 소용없음. 그냥 getUserInfo를 부름.
    }

    @PostMapping("/kakao-logout")
    public String kakaoLogout(){
        return "ok";
    }
}
