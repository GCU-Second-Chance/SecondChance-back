package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.KakaoLoginDto;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/kakao-login")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> kakaoLogin(@RequestBody KakaoLoginDto kakaoLoginDto) {
        String code = kakaoLoginDto.getCode();
        System.out.println("get code = " + code);

        System.out.println("request getAccessToken()");
        String accessToken = kakaoService.getAccessToken(code);
        System.out.println("response getAccessToken()");
        System.out.println("accessToken : " + accessToken);

        if (accessToken != null)
            return ResponseEntity.ok().body(accessToken);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/kakao-logout")
    public String kakaoLogout(@RequestBody KakaoLoginDto kakaoLoginDto){
        return "ok";
    }
}
