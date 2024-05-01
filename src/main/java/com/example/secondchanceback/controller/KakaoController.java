package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.KakaoLogoutDto;
import com.example.secondchanceback.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName : com.example.secondchanceback.controller
 * @FileName : KakaoLoginController
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@RestController("/v1/login")
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    @PostMapping("/kakao-login")
    public ResponseEntity<String> kakaoLogin(@RequestParam("code") String code){
        System.out.println("get code = " + code);

        System.out.println("request getAccessToken()");
        String accessToken = kakaoService.getAccessToken(code);
        System.out.println("response getAccessToken()");
        System.out.println("accessToken : " + accessToken);

        return ResponseEntity.ok().body(accessToken);
    }

    @PostMapping("/kakao-logout")
    public String kakaoLogout(@RequestBody KakaoLogoutDto kakaoLogoutDto){
        return "ok";
    }
}
