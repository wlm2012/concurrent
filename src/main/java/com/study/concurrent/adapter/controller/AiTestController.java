package com.study.concurrent.adapter.controller;

import com.study.concurrent.application.service.impl.AiServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiTestController {

    private final AiServiceImpl aiService;

    @GetMapping("/test")
    public String test(String input){
        return aiService.test(input);
    }
}
