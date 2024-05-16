package com.example.secondchanceback.dto;

/**
 * @PackageName : com.example.second_chance_back.dto
 * @FileName : OAuth2MemberInfo
 * @Author : noglass_gongdae
 * @Date : 2024-05-09
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface OAuth2Response {
    String getProviderId(); //공급자 id ex) 각각의 id
    String getProvider(); //공급자 ex) kakao, google
    String getName(); //사용자 이름 ex)홍길동
    String getEmail(); //사용자 이메일 ex)~@~.com
}
