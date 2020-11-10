package com.example.practice.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CustomMapperFactory {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    protected MapperFactory officeMapperFactoryBean() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    protected MapperFactory organizationMapperFactoryBean() {
        return new DefaultMapperFactory.Builder().build();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    protected MapperFactory userMapperFactoryBean() {
        return new DefaultMapperFactory.Builder().build();
    }
}
