package com.example.secondchanceback.service;

import com.example.secondchanceback.dto.KakaoLoginDto;
import com.example.secondchanceback.dto.KakaoUserInfoDto;
import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.UserEntity;
import java.util.Map;

/**
 * @PackageName : com.example.secondchanceback.service
 * @FileName : KakaoService
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface KakaoService {

    public KakaoLoginDto getAccessToken(String code);

    public UserDto getUserInfo(String accessToken);

    public String logout(String accessToken);

}
