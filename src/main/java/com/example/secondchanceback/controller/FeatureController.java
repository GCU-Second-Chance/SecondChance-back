package com.example.secondchanceback.controller;

import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/v1/feature")
@RequiredArgsConstructor
public class FeatureController {

    private final Logger LOGGER = LoggerFactory.getLogger(FeatureController.class);

    private final FeatureService featureService;

    @PostMapping("/donation")
    @CrossOrigin(origins = "http://localhost:5173/home")
    public ResponseEntity<DonationEntity> sharingUser(@RequestBody UserDto userDto){
        LOGGER.info("get UserDto : {}", userDto);
        UserEntity userEntity = featureService.donationUserUpdate(userDto);

        if(userEntity != null) {
            return featureService.donationAmountUpdate();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/takeaway")
    @CrossOrigin(origins = "http://localhost:5173/outro")
    public ResponseEntity<UserEntity> userTakeaway(@RequestBody UserDto userDto){
        return featureService.updateTakeaway(userDto);
    }
}
