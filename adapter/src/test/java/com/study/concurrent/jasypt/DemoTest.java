package com.study.concurrent.jasypt;

import jakarta.annotation.Resource;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * Description.
 *
 */
@Disabled
@SpringBootTest
public class DemoTest {

    @Resource
    StringEncryptor encryptor;

    @Test
//    @Disabled
    public void encrypt() {
        String url = encryptor.encrypt("jdbc:postgresql:");

        System.out.println("url = " + url);
    }
}