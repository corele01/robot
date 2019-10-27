package com.corele.robot.config;

import com.corele.robot.common.RedisComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisConfigTest {

    @Autowired
    private RedisComponent<String> redisComponent;

    @Test
    public void redisComponent() {
        this.redisComponent.set("test","ssss");
    }
}