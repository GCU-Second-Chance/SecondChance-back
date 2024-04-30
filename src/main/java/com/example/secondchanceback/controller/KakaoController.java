package com.example.secondchanceback.controller;

import com.example.secondchanceback.entity.KakaoLoginEntity;
import com.example.secondchanceback.entity.KakaoLogoutEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class KakaoController {

    @PostMapping("/kakao-login")
    public String kakaoLogin(@RequestBody KakaoLoginEntity kakaoLoginEntity){
        return "ok";
    }

    @PostMapping("/kakao-logout")
    public String kakaoLogout(@RequestBody KakaoLogoutEntity kakaoLogoutEntity){
        return "ok";
    }
}
