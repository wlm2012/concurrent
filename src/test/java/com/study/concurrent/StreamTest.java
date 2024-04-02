package com.study.concurrent;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class StreamTest {

    @Data
    public class Study {
        private String name;


        private String code;
    }

    @Test
    void test1() {
        ArrayList<Study> studyList = new ArrayList<>();
        Study study = new Study();

        studyList.add(study);
        studyList.add(null);

        studyList.stream()
                .forEach(System.out::println);

        studyList.stream()
                .map(Study::getName)
                .filter(StringUtils::hasText)
                .forEach(System.out::println);

//        System.out.println("collect = " + collect);

    }
}
