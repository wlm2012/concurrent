package com.study.concurrent.application.service.impl;

import com.study.concurrent.application.dto.request.ActorAddRequest;
import com.study.concurrent.domain.entity.ActorEntity;
import com.study.concurrent.domain.entity.ActorPhotoCollection;
import com.study.concurrent.domain.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl {

    private final ActorRepository actorRepository;

    public void save(ActorAddRequest actorAddRequest) {

        List<byte[]> photos = actorAddRequest.getPhotos();
        ArrayList<ActorPhotoCollection> photoCollections = new ArrayList<>();
        photos.forEach(e -> photoCollections.add(ActorPhotoCollection.builder()
                .photos(e)
                .build()));

        ActorEntity actorEntity = ActorEntity.builder()
                .name(actorAddRequest.getName())
                .photos(photoCollections)
                .build();

        actorRepository.save(actorEntity);
    }

    public ActorEntity queryByName(String name) {
        return actorRepository.findByName(name);
    }
}
