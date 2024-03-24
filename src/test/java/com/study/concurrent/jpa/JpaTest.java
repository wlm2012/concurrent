package com.study.concurrent.jpa;

import com.study.concurrent.domain.entity.ActorEntity;
import com.study.concurrent.domain.entity.ResourcesEntity;
import com.study.concurrent.domain.repository.ActorRepository;
import com.study.concurrent.domain.repository.ResourcesRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
public class JpaTest {

    @Resource
    private ActorRepository actorRepository;

    @Resource
    private ResourcesRepository resourcesRepository;


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
        resourcesEntity.setName("ipz2");

        ActorEntity actorEntity = new ActorEntity();
        actorEntity.setName("cang2");

        // 如果没有这一步，则会报错，因为没有 actor 的数据，但 actor_resources 会保存数据
        actorRepository.save(actorEntity);

        HashSet<ActorEntity> actorEntities = new HashSet<>();
        actorEntities.add(actorEntity);
        resourcesEntity.setActorSet(actorEntities);

        resourcesRepository.save(resourcesEntity);
    }


    /**
     * 即使加了cascade ，可以保存resources的数据
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
    void query_test() {
//        actorRepository.
    }
}
