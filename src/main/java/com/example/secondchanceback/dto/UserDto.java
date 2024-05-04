package com.example.secondchanceback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.secondchanceback.dto
 * @FileName : UserDto
 * @Author : noglass_gongdae
 * @Date : 2024-05-04
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String takeaway;

    public UserDto(Long id, String username){
        this.id = id;
        this.username = username;
    }
}
