package com.shengy.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: zhengying
 * @Date: 2019/7/10 00:36
 * @Description: TODO
 */
@Component
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try (InputStream inputStream = new FileInputStream("C:\\Users\\zhengying\\Desktop\\springboot\\springboot.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            PropertiesPropertySource propertySource = new PropertiesPropertySource("my", properties);
            environment.getPropertySources().addLast(propertySource);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
