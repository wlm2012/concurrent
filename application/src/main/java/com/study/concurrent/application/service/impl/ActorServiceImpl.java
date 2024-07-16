package com.study.concurrent.application.service.impl;

import com.study.concurrent.application.dto.request.ActorAddRequest;
import com.study.concurrent.domain.entity.ActorEntity;
import com.study.concurrent.domain.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl {

    private final ActorRepository actorRepository;

    public void save(ActorAddRequest actorAddRequest) {

        Set<byte[]> photos = actorAddRequest.getPhotos();

        ActorEntity actorEntity = ActorEntity.builder()
                .name(actorAddRequest.getName())
                .photos(photos)
                .build();
        actorRepository.save(actorEntity);
    }

    public ActorEntity queryByName(String name) {
        return actorRepository.findByName(name);
    }
}
