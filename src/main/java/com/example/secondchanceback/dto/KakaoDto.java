package com.example.secondchanceback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KakaoDto {

    @JsonProperty("access_token")
    private String accessToken;
}
