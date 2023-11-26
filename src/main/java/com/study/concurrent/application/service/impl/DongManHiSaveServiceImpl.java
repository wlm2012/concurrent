package com.study.concurrent.application.service.impl;

import com.study.concurrent.application.service.SaveService;
import com.study.concurrent.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class DongManHiSaveServiceImpl implements SaveService<String, String> {

    private final RestTemplate httpRestTemplate;

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Void> save(final String url, final String path) {

        log.info("path:{}", path);
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String name = url.substring(url.lastIndexOf("/"));
        log.info("start:{}", name);
        log.info(Thread.currentThread().toString());

        String filePath = path + File.separator + name;
        File file = new File(filePath);
        if (file.exists()) {
            return CompletableFuture.completedFuture(null);
        }

        HttpEntity<Object> httpEntity = getHttpHeaders();
        ResponseEntity<byte[]> entity = httpRestTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
        if (entity.getStatusCode().isError()) {
            throw new ServiceException("save wrong");
        }


        byte[] body = entity.getBody();
        if (body == null) {
            throw new ServiceException("save wrong");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath, true)) {
            fileOutputStream.write(body);
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("stop:{}", name);
        return CompletableFuture.completedFuture(null);
    }

    private static HttpEntity<Object> getHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Referer", "https://www.dongmanhi.com/");
        httpHeaders.add("Connection", "keep-alive");
        httpHeaders.add("Accept", "image/avif,image/webp,*/*");
        httpHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/117.0");

        return new HttpEntity<>(httpHeaders);
    }


    @Async("taskExecutor")
    public void sleep() {
        log.info(Thread.currentThread().toString());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
