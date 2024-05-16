package com.example.secondchanceback.dto.Impl;

import com.example.secondchanceback.dto.OAuth2Response;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PackageName : com.example.second_chance_back.dto.Impl
 * @FileName : KakaoMemberInfo
 * @Author : noglass_gongdae
 * @Date : 2024-05-09
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public class KakaoResponse implements OAuth2Response {
    private final Logger LOGGER = LoggerFactory.getLogger(KakaoResponse.class);
    private Map<String, Object> attributes;
    private Map<String, Object> kakaoAccountAttributes;
    private Map<String, Object> propertiesAttributes;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.kakaoAccountAttributes = (Map<String, Object>) attributes.get("kakao_account");
        this.propertiesAttributes = (Map<String, Object>) attributes.get("properties");
    }

    @Override
    public String getProviderId() {
        LOGGER.info("call KakaoResponse getProviderId()");
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getName() {
        LOGGER.info("call KakaoResponse getName()");
        return propertiesAttributes.get("nickname").toString();
    }

    @Override
    public String getEmail() {
        return null;
    }
}
