package com.shengy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 17:13
 * @Description: TODO
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args){
        SpringApplication app = new SpringApplication(Main.class);
//        app.setAdditionalProfiles("test");
        ConfigurableApplicationContext context = app.run(args);
        System.out.println(context.getEnvironment().getProperty("local.ip"));
        context.getBean(UserConfig.class).show();
        context.getBean(JdbcConfig.class).show();
        context.getBean(DataSourceProperties.class).show();
        System.out.println(context.getBean(TomcatProperties.class));
        System.out.println(context.getEnvironment().getProperty("springboot.name"));
        System.out.println(context.getEnvironment().getProperty("url_"));
        System.out.println(context.getEnvironment().getProperty("url_1"));
        context.getBeansOfType(Person.class).values().forEach(System.out::println);
        context.close();
    }
}
