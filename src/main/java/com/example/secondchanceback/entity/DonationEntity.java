package com.example.secondchanceback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.secondchanceback.entity
 * @FileName : foundatuonEntity
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "donation")
@ToString
public class DonationEntity {
    @Id
    private Long id;

    @Column
    private Long amount;
}
