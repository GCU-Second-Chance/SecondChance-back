package com.example.secondchanceback.service;

import com.example.secondchanceback.dto.CustomOAuth2User;
import com.example.secondchanceback.dto.Impl.KakaoResponse;
import com.example.secondchanceback.dto.OAuth2Response;
import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * @PackageName : com.example.second_chance_back.service
 * @FileName : CustomOAuth2UserService
 * @Author : noglass_gongdae
 * @Date : 2024-05-09
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public CustomOAuth2UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Value("${encoding.password}")
    private String password;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        LOGGER.info("CustomOAuth2UserService oAuth2User.getAttributes() : {}", oAuth2User.getAttributes());
        //System.out.println(oAuth2User.getAttributes());
        LOGGER.info("CustomOAuth2UserService userRequest.getClientRegistration().getRegistrationId() : {}", userRequest.getClientRegistration().getRegistrationId());
        //System.out.println(userRequest.getClientRegistration().getRegistrationId());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("kakao")) {
            LOGGER.info("CustomOAuth2UserService 로그인 성공");
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
            LOGGER.info("CustomOAuth2UserService kakaoResponse : {}", oAuth2Response);
        }
        else {
            LOGGER.info("CustomOAuth2UserService 로그인 실패");
            //System.out.println("로그인 실패");
            return null;
        }
        String provider = oAuth2Response.getProvider();
        String providerId = oAuth2Response.getProviderId();
        //LOGGER.info("CustomOAuth2UserService getProviderId() : {}", providerId);
        String username = provider + "_" + providerId;
        String name = oAuth2Response.getName();
        //LOGGER.info("CustomOAuth2UserService getName() : {}", name);
        String email = oAuth2Response.getEmail();
        String role = "ROLE_USER";
        Long sharingCount = 0L;
        String takeaway = "";

        Optional<UserEntity> findUser = userRepository.findByUsername(username);
        UserEntity userEntity = findUser.orElse(userRepository.save(
            UserEntity.builder()
                .username(username)
                .name(name)
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .sharingCount(sharingCount)
                .takeaway(takeaway)
                .build()
        ));
        UserDto userDto = UserDto.builder()
            .name(userEntity.getName())
            .role(userEntity.getRole())
            .username(userEntity.getUsername())
            .takeaway(userEntity.getTakeaway())
            .build();
        return new CustomOAuth2User(userDto, oAuth2User.getAttributes());
    }
}
