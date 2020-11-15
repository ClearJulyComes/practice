package com.example.practice.model.mapper;

import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.model.UserInfo;
import com.example.practice.view.officeview.OfficeIdView;
import com.example.practice.view.officeview.OfficeListView;
import com.example.practice.view.officeview.OfficeSaveDto;
import com.example.practice.view.officeview.OfficeUpdateDto;
import com.example.practice.view.organizationview.OrganizationIdView;
import com.example.practice.view.organizationview.OrganizationListView;
import com.example.practice.view.organizationview.OrganizationSaveDto;
import com.example.practice.view.organizationview.OrganizationUpdateDto;
import com.example.practice.view.userview.UserIdView;
import com.example.practice.view.userview.UserListView;
import com.example.practice.view.userview.UserSaveDto;
import com.example.practice.view.userview.UserUpdateDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CustomMapperFactory  {
    @Bean
    @Scope(scopeName = "singleton")
    public MapperFactory mapperFactory(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(UserInfo.class, UserIdView.class)
                .exclude("version")
                .exclude("version")
                .field("userDoc.doc.name", "docName")
                .field("userDoc.docNumber", "docNumber")
                .field("userDoc.docDate", "docDate")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault().register();
        mapperFactory.classMap(UserInfo.class, UserListView.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(UserSaveDto.class, UserInfo.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(UserUpdateDto.class, UserInfo.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(OrganizationIdView.class, Organization.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(Organization.class, OrganizationListView.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(OrganizationSaveDto.class, Organization.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(OrganizationUpdateDto.class, Organization.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(Office.class, OfficeIdView.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(Office.class, OfficeListView.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(OfficeSaveDto.class, Office.class)
                .exclude("version").byDefault().register();
        mapperFactory.classMap(OfficeUpdateDto.class, Office.class)
                .exclude("version").byDefault().register();
        return mapperFactory;
    }
}
