package com.example.practice.mappertests;

import com.example.practice.dto.OfficeDto;
import com.example.practice.dto.UserDto;
import com.example.practice.model.*;
import com.example.practice.model.mapper.CustomMapperFactory;
import ma.glasnost.orika.MapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomMapperFactory.class)
public class UserMapperTests {
    @Autowired
    @Qualifier("userMapperFactoryBean")
    private MapperFactory mapperFactory;

    @Test
    public void whenMap_returnMappedObject(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        Office office = new Office( 2, organization);
        Doc doc = new Doc(222, "Passport");
        Country country = new Country(11, "Russia");
        Date date = new Date(2011-11-22);
        UserDoc userDoc = new UserDoc(1, "6", date, doc);
        UserInfo userInfo = new UserInfo(1, "Me", office);
        userInfo.setCountryId(country);
        userInfo.setUserDocId(userDoc);
        userInfo.setIsIdentified(true);
        UserDto expected = new UserDto( 1, "Me", true, 2,
                11, "Russia",222, "Passport", "6", date);
        UserDto actual = mapperFactory.getMapperFacade().map(userInfo, UserDto.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenMap_returnMappedObjectFalse(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        Office office = new Office( 2, organization);
        Doc doc = new Doc(222, "Passport");
        Country country = new Country(11, "Russia");
        Date date = new Date(2011-11-22);
        UserDoc userDoc = new UserDoc(1, "6", date, doc);
        UserInfo userInfo = new UserInfo(1, "Me", office);
        userInfo.setCountryId(country);
        userInfo.setUserDocId(userDoc);
        userInfo.setIsIdentified(true);
        UserDto expected = new UserDto( 2, "ColaOffice", true, 3,
                11, "Russia",222, "Passport", "6", date);
        UserDto actual = mapperFactory.getMapperFacade().map(userInfo, UserDto.class);
        Assert.assertNotEquals(expected, actual);
    }
}
