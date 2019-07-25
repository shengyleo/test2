package com.shengy.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 23:07
 * @Description: TODO
 */
@Configuration
@PropertySources({@PropertySource("classpath:jdbc.properties"),
        @PropertySource("file:C:\\Users\\zhengying\\Desktop\\springboot\\jdbc.properties")})
public class FileConfig {

}
