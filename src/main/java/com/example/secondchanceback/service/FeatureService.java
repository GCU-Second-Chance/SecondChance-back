package com.example.secondchanceback.service;

import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import org.springframework.http.ResponseEntity;

/**
 * @PackageName : com.example.secondchanceback.service
 * @FileName : FeatureService
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface FeatureService {

    public UserEntity donationUserUpdate(UserDto userDto);

    public ResponseEntity<DonationEntity> donationAmountUpdate();

    public ResponseEntity<UserEntity> updateTakeaway(UserDto userDto);

}
