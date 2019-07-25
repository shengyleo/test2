package com.shengy.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 01:05
 * @Description: TODO
 */
@Configuration
public class App2 {
    @Bean
    public Runnable createRunable(){
        return ()->{
            System.out.println("springboot-demo01 is run");
        };
    }
}
