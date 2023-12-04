package com.study.concurrent.adapter.controller;

import com.study.concurrent.adapter.dto.IdsDO;
import com.study.concurrent.application.service.impl.EbookServiceImpl;
import com.study.concurrent.repository.po.EbookPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class EbookController {

    private final EbookServiceImpl ebookService;

    @GetMapping("/findByIds")
    public List<EbookPO> findByIds(IdsDO idsDO) {
        log.info(Thread.currentThread().isVirtual() + Thread.currentThread().getName());
        log.info(Thread.currentThread().toString());

        ebookService.asyncFindByIds();
        return ebookService.findByIds(idsDO.getIds());
    }
}
