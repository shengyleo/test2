package com.shengy.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Auther: zhengying
 * @Date: 2019/7/11 23:41
 * @Description: TODO
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Person person = null;
        for (int i=0;i<10;i++){
            person = new Person("z"+i,10+ new Random(50).nextInt());
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
            builder.addPropertyValue("name","a"+i);
            builder.addPropertyValue("age",10+i);

            beanDefinitionRegistry.registerBeanDefinition("name"+i,builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
