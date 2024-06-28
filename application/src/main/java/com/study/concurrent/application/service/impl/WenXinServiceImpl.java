package com.study.concurrent.application.service.impl;

import com.study.concurrent.client.wenXin.WenXinClient;
import com.study.concurrent.client.wenXin.WenXinTokenClient;
import com.study.concurrent.dto.request.WenXinRequest;
import com.study.concurrent.dto.response.WenXinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WenXinServiceImpl {

    private final WenXinTokenClient wenXinTokenClient;

    private final WenXinClient wenXinClient;

    public WenXinResponse getWenXin(WenXinRequest request) {
        String wenXinToken = wenXinTokenClient.getWenXinToken();
        return wenXinClient.getWenXin(request, wenXinToken);
    }


}
