package com.example.secondchanceback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.second_chance_back.dto
 * @FileName : UserDto
 * @Author : noglass_gongdae
 * @Date : 2024-05-10
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String username;
    private String name;
    private String role;
    private String takeaway;
}
