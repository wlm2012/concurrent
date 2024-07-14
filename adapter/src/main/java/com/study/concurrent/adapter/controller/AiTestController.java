package com.study.concurrent.adapter.controller;

import com.study.concurrent.adapter.vo.WenXinVO;
import com.study.concurrent.application.service.impl.AiServiceImpl;
import com.study.concurrent.application.service.impl.WenXinServiceImpl;
import com.study.concurrent.dto.request.WenXinRequest;
import com.study.concurrent.dto.response.WenXinResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiTestController {

    private final AiServiceImpl aiService;

    private final WenXinServiceImpl wenXinService;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(String input) {
//        return aiService.test(input);
        return null;
    }

    @PostMapping("/wenXin")
    public WenXinVO wenXin(@RequestBody WenXinRequest request) {
        WenXinResponse response = wenXinService.getWenXin(request);
        return WenXinVO.builder().
                result(response.result())
                .build();
    }


}
