package com.shengy.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 23:10
 * @Description: TODO
 */
@Component
public class JdbcConfig {
    @Value("${url}")
    private String url;
    @Value("${driverClass}")
    private String driverClassName;

    @Value("${uname}")
    private String username;
    @Value("${password}")
    private String password;
    public void show(){
        System.out.println("----------");
        System.out.println("url:"+url);
        System.out.println("driverClassName:"+driverClassName);
        System.out.println("username:"+username);
        System.out.println("password:"+password);
    }
}
