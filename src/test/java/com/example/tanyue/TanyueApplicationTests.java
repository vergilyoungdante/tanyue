package com.example.tanyue;

import com.example.tanyue.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TanyueApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    //测试数据库连接是否正常
    @Test
    public void databaseTest() {
        userRepository.save(new User("aaa","bbb",1));
    }
}
