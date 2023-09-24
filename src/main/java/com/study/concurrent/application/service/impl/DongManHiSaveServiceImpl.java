package com.study.concurrent.application.service.impl;

import com.study.concurrent.application.service.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DongManHiSaveServiceImpl implements SaveService<String, String> {

    private final RestTemplate httpRestTemplate;

    @Override
    public void save(final String url, final String path) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Referer", "https://www.dongmanhi.com/");
        httpHeaders.add("Connection","keep-alive");
        httpHeaders.add("Accept", "image/avif,image/webp,*/*");
        httpHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> entity = httpRestTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
        if (entity.getStatusCode().isError()) {
            log.error("save wrong");
            return;
        }

        String name = url.substring(url.lastIndexOf("/"));

        byte[] body = entity.getBody();

        if (body == null) {
            log.error("save wrong");
            return;
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator + name, true)) {
            fileOutputStream.write(body);
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
