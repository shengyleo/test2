package com.shengy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
public class App 
{

    public static void main( String[] args )
    {
//        ConfigurableApplicationContext context = SpringApplication.run(App2.class, args);
        SpringApplication application = new SpringApplication();
        Set classes = new HashSet();
        classes.add(App2.class);
        classes.add(MyConfig.class);
        application.setSources(classes);
        ConfigurableApplicationContext context = application.run(args);
        context.getBean(Runnable.class).run();
        System.out.println(context.getBean(List.class));
//        System.out.println(context.getBean(User.class));
    }
}
