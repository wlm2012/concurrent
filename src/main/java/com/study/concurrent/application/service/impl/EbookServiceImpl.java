package com.study.concurrent.application.service.impl;

import com.study.concurrent.repository.jpa.EbookRepository;
import com.study.concurrent.repository.po.EbookPO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class EbookServiceImpl {

    private final EbookRepository ebookRepository;


    public List<EbookPO> findByIds(List<Long> ids) {
        return ebookRepository.findByIds(ids);
    }

    @Async
    public CompletableFuture<List<EbookPO>> asyncFindByIds(List<Long> ids) {
        log.info(Thread.currentThread().isVirtual() + Thread.currentThread().getName());
        log.info(Thread.currentThread().toString());
        return CompletableFuture.completedFuture(ebookRepository.findByIds(ids));
    }

}
