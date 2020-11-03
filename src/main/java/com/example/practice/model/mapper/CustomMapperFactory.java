package com.example.practice.model.mapper;

import com.example.practice.dto.OfficeDto;
import com.example.practice.dto.OrganizationDto;
import com.example.practice.dto.UserDto;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.model.UserInfo;
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
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Office.class, OfficeDto.class)
                .exclude("version").field("orgId.id", "orgId").byDefault().register();
        return mapperFactory;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    protected MapperFactory organizationMapperFactoryBean() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Organization.class, OrganizationDto.class)
                .exclude("version").byDefault().register();
        return mapperFactory;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    protected MapperFactory userMapperFactoryBean() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserInfo.class, UserDto.class)
                .exclude("version").field("officeId.id", "officeId")
                .field("countryId.code", "citizenshipCode")
                .field("countryId.name", "citizenshipName")
                .field("userDocId.docNumber", "docNumber")
                .field("userDocId.docId.name", "docName")
                .field("userDocId.docId.code", "docCode")
                .field("userDocId.docDate", "docDate").byDefault().register();
        return mapperFactory;
    }
}
