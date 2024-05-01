package com.example.secondchanceback.service;

/**
 * @PackageName : com.example.secondchanceback.service
 * @FileName : KakaoService
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface KakaoService {

    public String getAccessToken(String code);

    public String getUserInfo(String accessToken);

    public String logout(String accessToken);

}
