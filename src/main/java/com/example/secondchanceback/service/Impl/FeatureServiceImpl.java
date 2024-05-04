package com.example.secondchanceback.service.Impl;

import com.example.secondchanceback.dto.UserDto;
import com.example.secondchanceback.entity.DonationEntity;
import com.example.secondchanceback.entity.UserEntity;
import com.example.secondchanceback.repository.DonationRepository;
import com.example.secondchanceback.repository.UserRepository;
import com.example.secondchanceback.service.FeatureService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @PackageName : com.example.secondchanceback.service.Impl
 * @FileName : FeatureServiceImpl
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final Logger LOGGER = LoggerFactory.getLogger(FeatureServiceImpl.class);

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;


    @Override
    @Transactional
    public UserEntity donationUserUpdate(UserDto userDto) {
        Optional<UserEntity> userEntity = findUser(userDto);

        return userEntity.map(entity -> {
            entity.setSharing(entity.getSharing() + 1);
            return updateSharing(entity);
        }).orElse(null);
    }

    @Override
    @Transactional
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
    @Transactional
    public ResponseEntity<UserEntity> updateTakeaway(UserDto userDto) {
        ResponseEntity<UserEntity> responseEntity;
        Optional<UserEntity> userEntity = findUser(userDto);

        responseEntity = userEntity.map(entity -> {
            entity.setTakeaway(userDto.getTakeaway());
            return new ResponseEntity<>(updateUserTakeaway(entity), HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        return responseEntity;
    }

    private Optional<UserEntity> findUser(UserDto userDto){
        return userRepository.findById(userDto.getId());
    }
    private Optional<DonationEntity> findDonation(Long id){
        return donationRepository.findById(id);
    }
    private UserEntity updateSharing(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
    private DonationEntity updateDonation(DonationEntity donationEntity){
        return donationRepository.save(donationEntity);
    }
    private UserEntity updateUserTakeaway(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}
