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
        String url = encryptor.encrypt("jdbc:postgresql://47.102.127.65:5433/dbc33362342ece630249b090faa5fb78f9bb30study");
        String username = encryptor.encrypt("pos342tgre");
        String pwd = encryptor.encrypt("HgmUXNXi42342KZp4NBj");
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("pwd = " + pwd);
    }
}