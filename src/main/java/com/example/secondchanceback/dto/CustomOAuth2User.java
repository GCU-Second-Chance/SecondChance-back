package com.example.secondchanceback.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @PackageName : com.example.second_chance_back.dto
 * @FileName : CustomOAuth2User
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
public class CustomOAuth2User implements OAuth2User {

    private UserDto userDto;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDto.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        //return "name";
        return userDto.getName();
    }

    public String getUsername(){
        return userDto.getUsername();
    }
}
