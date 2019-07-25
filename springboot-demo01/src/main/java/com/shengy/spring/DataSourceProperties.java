package com.shengy.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhengying
 * @Date: 2019/7/9 23:42
 * @Description: TODO
 */
@Component
@ConfigurationProperties(prefix="ds")
@PropertySource("classpath:ds.properties")
public class DataSourceProperties {
    private String url;
    private String driverClassName;
    private String userName;
    private String password;
    public void show(){
        System.out.println("url--->"+url);
        System.out.println("driverClassName--->"+driverClassName);
        System.out.println("username--->"+userName);
        System.out.println("password--->"+password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
