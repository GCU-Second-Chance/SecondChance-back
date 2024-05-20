package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.CustomOAuth2User;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName : com.example.secondchanceback.controller
 * @FileName : FeatureController
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/feature")
@CrossOrigin (origins = "https://web-secondchance-front-test2-1cupyg2klvnmgdft.sel5.cloudtype.app")
public class FeatureController {

    private final Logger LOGGER = LoggerFactory.getLogger(FeatureController.class);

    private final FeatureService featureService;

    @GetMapping("/donation")
    public ResponseEntity<DonationEntity> sharingUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("FeatureController_sharingUser_authentication : {}", authentication);
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String username = customOAuth2User.getUsername();
        LOGGER.info("FeatureController_sharingUser_username : {}", username);

        UserEntity userEntity = featureService.donationUserUpdate(username);

        if(userEntity != null) {
            return featureService.donationAmountUpdate();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/takeaway")
    public ResponseEntity<UserEntity> userTakeaway(@RequestBody String takeaway){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String username = customOAuth2User.getUsername();

        LOGGER.info("FeatureController_userTakeaway_username username : {}", username);

        return featureService.updateTakeaway(takeaway, username);
    }
}
