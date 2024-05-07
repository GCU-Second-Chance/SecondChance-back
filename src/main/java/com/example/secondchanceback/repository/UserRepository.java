package com.example.secondchanceback.repository;

import com.example.secondchanceback.entity.UserEntity;
import io.micrometer.common.lang.NonNullApi;
import jakarta.annotation.Nonnull;
import java.util.Optional;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @PackageName : com.example.secondchanceback.repository
 * @FileName : UserRepository
 * @Author : noglass_gongdae
 * @Date : 2024-04-30
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Qualifier("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public boolean existsById(Long id);

    public Optional<UserEntity> findById(Long id);

}
