package com.study.concurrent.adapter.controller;

import com.study.concurrent.application.service.QueryService;
import com.study.concurrent.application.service.ResolveService;
import com.study.concurrent.application.service.SaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CrawlerController {


    private final Map<String, QueryService<String, String>> queryServiceMap;

    private final Map<String, ResolveService<List<String>, String>> resolveServiceMap;

    private final Map<String, SaveService<String, String>> saveServiceMap;


    @PostMapping("/crawler")
    public void crawler(String url, String path) {
        QueryService<String, String> queryService = queryServiceMap.get("dongManHiQueryServiceImpl");
        ResolveService<List<String>, String> resolveService = resolveServiceMap.get("dongManHiResolveServiceImpl");
        SaveService<String, String> saveService = saveServiceMap.get("dongManHiSaveServiceImpl");

        String query = queryService.query(url);
        List<String> resolve = resolveService.resolve(query);


        for (String s : resolve) {
            saveService.save(s, path);
        }

    }
}
