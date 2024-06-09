package com.study.concurrent.client;

import com.study.concurrent.dto.response.WenXinTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WenXinTokenClient {

    @Value("${wenxin.tokenUrl}")
    private String tokenUrl;

    @Value("${wenxin.apiKey}")
    private String apiKey;

    @Value("${wenxin.secretKey}")
    private String secretKey;

    private final RestTemplate httpRestTemplate;
    private String token;

    public String getWenXinToken() {
        if (StringUtils.hasText(token)) {
            return token;
        }
        String url = tokenUrl + "?grant_type=client_credentials&client_id=" + apiKey + "&client_secret=" + secretKey;
        ResponseEntity<WenXinTokenResponse> response = httpRestTemplate.postForEntity(url, null, WenXinTokenResponse.class);
        token = response.getBody().accessToken();
        return token;

    }

}
