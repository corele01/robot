package com.corele.robot.common;

import com.corele.robot.model.RobotFairySearchConfig;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import static org.junit.Assert.*;

public class AbstractServiceTest {


    @Test
    public void test(){
        AbstractService<RobotFairySearchConfig> abstractService = new AbstractService<RobotFairySearchConfig>() {
            @Override
            protected Example getExample() {
                return super.getExample();
            }
        };
    }

}