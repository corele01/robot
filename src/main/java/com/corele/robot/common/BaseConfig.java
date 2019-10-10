package com.corele.robot.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jun
 */
@Configuration
@Slf4j
public class BaseConfig {
    @Autowired
    private ResourceProperties resourceProperties;

    @Bean
    public void init(){
        String[] staticLocations = this.resourceProperties.getStaticLocations();
        log.info("{}",staticLocations);
    }
}
