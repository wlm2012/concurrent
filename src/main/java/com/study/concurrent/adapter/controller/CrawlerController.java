package com.study.concurrent.adapter.controller;

import com.study.concurrent.application.service.QueryService;
import com.study.concurrent.application.service.ResolveService;
import com.study.concurrent.application.service.SaveService;
import com.study.concurrent.application.service.impl.DongManHiSaveServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {


    private final QueryService<String, String> dongManHiQueryServiceImpl;

    private final ResolveService<List<String>, String> dongManHiResolveServiceImpl;

    private final SaveService<String, String> dongManHiSaveServiceImpl;

    private final DongManHiSaveServiceImpl dongManHiSaveService;


    @PostMapping("/crawler")
    public void crawler(String url, String path) {

        for (int i = 83837; i < 83874; i++) {
            String query = dongManHiQueryServiceImpl.query(url + "/" + i + ".html");
            List<String> resolve = dongManHiResolveServiceImpl.resolve(query);

            ArrayList<CompletableFuture<Void>> completableFutures = new ArrayList<>();
            for (String s : resolve) {
                completableFutures.add(dongManHiSaveServiceImpl.save(s, path + File.separator + (i - 83805)));
            }

            CompletableFuture<Void> future = CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new));

            future.exceptionally(e -> {
                log.error("wrong", e);
                return null;
            }).join();
            log.info("finish");
        }


    }

    @PostMapping("/sleep")
    public void sleep() {
        log.info(Thread.currentThread().isVirtual() + Thread.currentThread().getName());
        dongManHiSaveService.sleep();
    }
}
