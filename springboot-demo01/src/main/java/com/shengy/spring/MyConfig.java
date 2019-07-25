package com.shengy.spring;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 01:20
 * @Description: TODO
 */
@SpringBootConfiguration
public class MyConfig {
    @Bean
    public List createList(){
        return new ArrayList();
    }
    @Bean
    public Runnable createRunnable(){
        System.out.println("=====1=======");
        return ()->{};
    }
    @Bean
    @Profile("dev")
    public Runnable createRunnable2(){
        System.out.println("=====2=======");
        return ()->{};
    }
    @Bean
    @Profile("test")
    public Runnable createRunnable3(){
        System.out.println("=====3=======");
        return ()->{};
    }
}
