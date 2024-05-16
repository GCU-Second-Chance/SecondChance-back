package com.example.secondchanceback.repository;

import com.example.secondchanceback.entity.DonationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PackageName : com.example.second_chance_back.repository
 * @FileName : DonationRepository
 * @Author : noglass_gongdae
 * @Date : 2024-05-12
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

    Optional<DonationEntity> findById(Long id);

}
