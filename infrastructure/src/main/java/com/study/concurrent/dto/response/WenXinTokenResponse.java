package com.study.concurrent.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WenXinTokenResponse(
        @JsonProperty("access_token")
        String accessToken) {
}
