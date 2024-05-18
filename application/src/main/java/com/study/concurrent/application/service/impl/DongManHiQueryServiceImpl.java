package com.study.concurrent.application.service.impl;

import com.study.concurrent.CommonDO.exception.ServiceException;
import com.study.concurrent.application.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DongManHiQueryServiceImpl implements QueryService<String, String> {

    private final RestTemplate httpRestTemplate;

    @Override
    public String query(final String s) {
        ResponseEntity<String> responseEntity =
                httpRestTemplate.getForEntity(s, String.class);
        if (responseEntity.getStatusCode().isError()) {
            throw new ServiceException("wrong");
        }
        return responseEntity.getBody();
    }
}
