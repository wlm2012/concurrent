package com.study.concurrent.adapter.controller;

import com.study.concurrent.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {


    private final RestTemplate httpRestTemplate;


    @PostMapping("/crawler")
    public void crawler() throws InterruptedException {
        ResponseEntity<String> responseEntity =
                httpRestTemplate.getForEntity("https://www.dongmanhi.com/manhua/2251/48847.html", String.class);
        if (responseEntity.getStatusCode().isError()) {
            throw new ServiceException("wrong");
        }


        String body = responseEntity.getBody();
        System.out.println(body);
    }
}
