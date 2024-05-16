package com.example.secondchanceback.repository;

import com.example.secondchanceback.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PackageName : com.example.second_chance_back.repository
 * @FileName : UserRepository
 * @Author : noglass_gongdae
 * @Date : 2024-05-09
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username);

}
