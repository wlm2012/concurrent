package com.study.concurrent.adapter.controller;

import com.study.concurrent.application.service.QueryService;
import com.study.concurrent.application.service.ResolveService;
import com.study.concurrent.application.service.SaveService;
import com.study.concurrent.application.service.impl.DongManHiSaveServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {


    private final Map<String, QueryService<String, String>> queryServiceMap;

    private final Map<String, ResolveService<List<String>, String>> resolveServiceMap;

    private final Map<String, SaveService<String, String>> saveServiceMap;

    private final DongManHiSaveServiceImpl dongManHiSaveService;


    @PostMapping("/crawler")
    public void crawler(String url, String path) {
        QueryService<String, String> queryService = queryServiceMap.get("dongManHiQueryServiceImpl");
        ResolveService<List<String>, String> resolveService = resolveServiceMap.get("dongManHiResolveServiceImpl");
        SaveService<String, String> saveService = saveServiceMap.get("dongManHiSaveServiceImpl");

        String query = queryService.query(url);
        List<String> resolve = resolveService.resolve(query);

        ArrayList<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        for (String s : resolve) {
            completableFutures.add(saveService.save(s, path));
        }

        CompletableFuture<Void> future = CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new));

        future.exceptionally(e -> {
            log.error("wrong", e);
            return null;
        }).join();
        log.info("finish");

    }

    @PostMapping("/sleep")
    public void sleep() {
        log.info(Thread.currentThread().isVirtual() + Thread.currentThread().getName());
        dongManHiSaveService.sleep();
    }
}
