package com.example.secondchanceback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.secondchanceback.dto
 * @FileName : KakaoResponseUserInfoDto
 * @Author : noglass_gongdae
 * @Date : 2024-05-02
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoUserInfoDto {
    @JsonProperty("id")
    private Long id;
//    @JsonProperty("connected_at")
//    private LocalDateTime connected_at;
//    @JsonProperty("synched_at")
//    private LocalDateTime synched_at;
    @JsonProperty("properties")
    private JsonObject properties;
}
