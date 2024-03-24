package com.study.concurrent.jpa;

import com.study.concurrent.domain.repository.ActorRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaTest {

    @Resource
    private ActorRepository actorRepository;


    @Test
    void query_test() {

    }
}
