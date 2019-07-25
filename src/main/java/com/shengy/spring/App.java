package com.shengy.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import static java.lang.System.*;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    @Bean
    public Runnable createRunnable(){
        return () ->{
            out.println("springboot is run!!");
        };
    }
    @Bean
    public Thread createThread(){

        return  new Thread(){
            @Override
            public void run() {
                System.out.println("currentThread "+currentThread());
            }
        };
    }

    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(Thread.class).start();
    }
}
