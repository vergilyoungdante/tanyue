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

    @Test
    public void databaseTest() {
        userRepository.save(new User("aaa","bbb",1));
    }
}
