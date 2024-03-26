package com.study.concurrent.jpa;

import com.study.concurrent.domain.entity.ActorEntity;
import com.study.concurrent.domain.entity.AuthorEntity;
import com.study.concurrent.domain.entity.EbookEntity;
import com.study.concurrent.domain.entity.ResourcesEntity;
import com.study.concurrent.domain.repository.ActorRepository;
import com.study.concurrent.domain.repository.AuthorRepository;
import com.study.concurrent.domain.repository.EbookRepository;
import com.study.concurrent.domain.repository.ResourcesRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class JpaTest {

    @Resource
    private ActorRepository actorRepository;

    @Resource
    private ResourcesRepository resourcesRepository;

    @Resource
    private EbookRepository ebookRepository;

    @Resource
    private AuthorRepository authorRepository;


    /**
     * mappedBy 控制外键由谁控制
     * 所以导致关联表 actor_resources 没有保存数据
     */
    @Test
    void save_one() {
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setName("ipz");

        // 如果没有这一步，则无法保存 resources
        resourcesRepository.save(resourcesEntity);

        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("cang");

        HashSet<ResourcesEntity> resourcesEntitySet = new HashSet<>();
        resourcesEntitySet.add(resourcesEntity);
        actorEntity.setResourcesSet(resourcesEntitySet);

        actorRepository.save(actorEntity);
    }


    /**
     * mappedBy 控制外键由谁控制
     * 所以关联表 actor_resources 保存了数据
     */
    @Test
    void save_two() {
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setName("ipz9");

        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("cang9");

        // 如果没有这一步，则会报错，因为没有 actor 的数据，但 actor_resources 会保存数据
        actorRepository.save(actorEntity);

        HashSet<ActorEntity> actorEntities = new HashSet<>();
        actorEntities.add(actorEntity);
        resourcesEntity.setActorSet(actorEntities);

        resourcesRepository.save(resourcesEntity);
    }


    /**
     * 加了cascade ，可以保存resources的数据
     * 但关联表 actor_resources 由mappedBy控制，所以没有保存数据
     */
    @Test
    void save_one_cascade() {
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setName("ipz");

        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("cang");

        HashSet<ResourcesEntity> resourcesEntitySet = new HashSet<>();
        resourcesEntitySet.add(resourcesEntity);
        actorEntity.setResourcesSet(resourcesEntitySet);

        actorRepository.save(actorEntity);
    }


    /**
     * 加了cascade ，可以保存 actor 的数据
     * 关联表 actor_resources 由mappedBy控制，所以保存了数据
     */
    @Test
    void save_two_cascade() {
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setName("ipz2");

        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("cang2");


        HashSet<ActorEntity> actorEntities = new HashSet<>();
        actorEntities.add(actorEntity);
        resourcesEntity.setActorSet(actorEntities);

        resourcesRepository.save(resourcesEntity);
    }


    @Test
    @Transactional
    void query_test() {
        Optional<ActorEntity> actorEntityOptional = actorRepository.findById(802L);
        if (actorEntityOptional.isPresent()) {
            ActorEntity actorEntity = actorEntityOptional.get();
            log.info("actorEntity" + actorEntity);
            log.info("----------------------------------");
            log.info("ResourcesSet().size" + actorEntity.getResourcesSet().size());
        }

    }


    @Test
    @Transactional
    void query_ebook_test() {
        Optional<EbookEntity> ebookEntityOptional = ebookRepository.findById(1L);
        if (ebookEntityOptional.isPresent()) {
            EbookEntity ebookEntity = ebookEntityOptional.get();
            log.info("ebookEntity" + ebookEntity);
            log.info("------------------------------------");
            log.info("AuthorEntity" + ebookEntity.getAuthorEntity());
        }
    }


    @Test
    @Transactional
    void query_author_test() {
        Optional<AuthorEntity> authorEntityOptional = authorRepository.findById(1L);
        if (authorEntityOptional.isPresent()) {
            AuthorEntity authorEntity = authorEntityOptional.get();
            log.info("authorEntity" + authorEntity);
            log.info("------------------------------------");
            log.info("EbookEntitySet" + authorEntity.getEbookEntitySet());
        }
    }

    @Test
    @Transactional
    void query_author_all_test() {
        List<AuthorEntity> all = authorRepository.findAll();

        for (AuthorEntity authorEntity : all) {
            log.info("EbookEntitySet" + authorEntity.getEbookEntitySet());
        }
    }


    @Test
    void query_book_all_test() {
        List<EbookEntity> allBook = ebookRepository.findAllBook();
        for (EbookEntity ebookEntity : allBook) {
            log.info("getAuthorEntity" + ebookEntity.getAuthorEntity());
        }
    }
}
