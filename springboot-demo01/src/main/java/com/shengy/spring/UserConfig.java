package com.shengy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 17:25
 * @Description: TODO
 */
@Component
public class UserConfig {
    @Value("${local.port}")
    private int localPort;
    @Value("${app.name}")
    private String appname;
    @Value("${tomcat.port:9090}")
    private String tomcatPort;

    @Autowired
    private Environment environment;
    public void show(){
        System.out.println("local.ip="+environment.getProperty("local.ip"));
        System.out.println("local.port(to integer)="+environment.getProperty("local.port",Integer.class));
        System.out.println("local.port="+localPort);
        System.out.println("name="+environment.getProperty("name"));
        System.out.println("app.name="+appname);
        System.out.println("tomcat.port="+tomcatPort);
    }
}
