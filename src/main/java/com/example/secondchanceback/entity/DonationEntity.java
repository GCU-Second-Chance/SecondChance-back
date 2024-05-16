package com.example.secondchanceback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.second_chance_back.entity
 * @FileName : DonationEntity
 * @Author : noglass_gongdae
 * @Date : 2024-05-12
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation")
@ToString
public class DonationEntity {
    @Id
    private Long id;

    private Long amount;
}
