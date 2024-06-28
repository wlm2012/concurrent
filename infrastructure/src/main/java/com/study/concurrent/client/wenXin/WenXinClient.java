package com.study.concurrent.client.wenXin;

import com.study.concurrent.dto.request.WenXinRequest;
import com.study.concurrent.dto.response.WenXinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WenXinClient {

    private final RestTemplate httpRestTemplate;

    @Value("${wenxin.wenXinErnieSpeed128kUrl}")
    private String wenXinSpeed128kUrl;

    @Value("${wenxin.wenXinErnieSpeedUrl}")
    private String wenXinSpeedUrl;

    public WenXinResponse getWenXin(WenXinRequest wenXinRequest,String accessToken) {
        String url = wenXinSpeed128kUrl + "?access_token=" + accessToken;
        ResponseEntity<WenXinResponse> responseEntity = httpRestTemplate.postForEntity(url, wenXinRequest, WenXinResponse.class);
        return responseEntity.getBody();
    }
}
