package com.example.secondchanceback.service.impl;

import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.repository.DonationRepository;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.FeatureService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @PackageName : com.example.second_chance_back.service
 * @FileName : FeatureServiceImpl
 * @Author : noglass_gongdae
 * @Date : 2024-05-12
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(FeatureServiceImpl.class);
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    public FeatureServiceImpl(UserRepository userRepository, DonationRepository donationRepository){
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public UserEntity donationUserUpdate(String username) {
        Optional<UserEntity> userEntity = findUser(username);
        LOGGER.info("findUser : {}", userEntity);
        return userEntity.map(entity -> {
            LOGGER.info("entity : {}", entity);
            entity.setSharingCount(entity.getSharingCount() + 1);
            return updateSharing(entity);
        }).orElse(null);
    }

    @Override
    public ResponseEntity<DonationEntity> donationAmountUpdate() {
        ResponseEntity<DonationEntity> responseEntity;

        Optional<DonationEntity> donationEntity = findDonation(1L);
        responseEntity = donationEntity.map(entity -> {
            entity.setAmount(entity.getAmount() + 1);
            return new ResponseEntity<>(updateDonation(entity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        return responseEntity;
    }
    @Override
    public ResponseEntity<UserEntity> updateTakeaway(String takeaway, String username) {
        LOGGER.info("updateTakeaway(), takeaway : {}, username : {}", takeaway, username);
        ResponseEntity<UserEntity> responseEntity;
        Optional<UserEntity> userEntity = findUser(username);

        responseEntity = userEntity.map(entity -> {
            LOGGER.info("entity : {}", entity);
            entity.setTakeaway(takeaway);
            return new ResponseEntity<>(updateUserTakeaway(entity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        return responseEntity;
    }

    private Optional<UserEntity> findUser(String username){
        LOGGER.info("findUser(), username : {} ", username);
        return userRepository.findByUsername(username);
    }
    private Optional<DonationEntity> findDonation(Long id){
        LOGGER.info("findDonation, id : {} ", id);
        return donationRepository.findById(id);
    }
    private UserEntity updateSharing(UserEntity userEntity){
        LOGGER.info("updateSharing, userEntity : {} ", userEntity);
        return userRepository.save(userEntity);
    }
    private DonationEntity updateDonation(DonationEntity donationEntity){
        LOGGER.info("updateDonation, donationEntity : {} ", donationEntity);
        return donationRepository.save(donationEntity);
    }
    private UserEntity updateUserTakeaway(UserEntity userEntity){
        LOGGER.info("updateUserTakeaway(), userEntity : {}", userEntity);
        return userRepository.save(userEntity);
    }
}