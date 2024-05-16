package com.example.secondchanceback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.second_chance_back.entity
 * @FileName : UserEntity
 * @Author : noglass_gongdae
 * @Date : 2024-05-09
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "all_user")
@Entity
public class UserEntity {
    @Id
    private String username;
    private String name;
    private String email;
    private String password;
    private String role;
    private String provider;
    private String providerId;
    private Long sharingCount;
    private String takeaway;
}
