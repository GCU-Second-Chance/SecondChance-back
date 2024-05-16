package com.example.secondchanceback.service;

import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import org.springframework.http.ResponseEntity;

/**
 * @PackageName : com.example.second_chance_back.service
 * @FileName : FeatureService
 * @Author : noglass_gongdae
 * @Date : 2024-05-12
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface FeatureService {

    public UserEntity donationUserUpdate(String username);

    public ResponseEntity<DonationEntity> donationAmountUpdate();

    public ResponseEntity<UserEntity> updateTakeaway(String takeaway, String username);


}
