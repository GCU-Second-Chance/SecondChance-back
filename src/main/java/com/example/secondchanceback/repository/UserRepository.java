package com.example.secondchanceback.repository;

import com.example.secondchanceback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PackageName : com.example.secondchanceback.repository
 * @FileName : UserRepository
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
