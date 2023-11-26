package com.study.concurrent.application.service.impl;

import com.study.concurrent.repository.jpa.EbookRepository;
import com.study.concurrent.repository.po.EbookPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl {

    private final EbookRepository ebookRepository;

    public List<EbookPO> findByIds(List<Long> ids) {
        return ebookRepository.findByIds(ids);
    }

}
