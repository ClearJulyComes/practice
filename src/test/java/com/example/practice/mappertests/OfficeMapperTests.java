package com.example.practice.mappertests;

import com.example.practice.dto.OfficeDto;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.CustomMapperFactory;
import ma.glasnost.orika.MapperFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomMapperFactory.class)
public class OfficeMapperTests {

    @Autowired
    @Qualifier("officeMapperFactoryBean")
    private MapperFactory mapperFactory;

    @Test
    public void whenMap_returnMappedObject(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        Office office = new Office( 2, organization);
        office.setIsActive(true);
        office.setName("ColaOffice");
        office.setPhone("89988");
        office.setVersion(0);
        office.setAddress("Moscow, 22");
        OfficeDto expected = new OfficeDto( 2, "ColaOffice", "89988",
                "Moscow, 22", true, 1);
        OfficeDto actual = mapperFactory.getMapperFacade().map(office, OfficeDto.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenMap_returnMappedObjectFalse(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        Office office = new Office( 2, organization);
        office.setIsActive(true);
        office.setName("ColaOffice");
        office.setPhone("89988");
        office.setVersion(0);
        office.setAddress("Moscow, 22");
        OfficeDto expected = new OfficeDto( 2, "ColaOfficeFalse", "89988",
                "Moscow, 22", true, 1);
        OfficeDto actual = mapperFactory.getMapperFacade().map(office, OfficeDto.class);
        Assert.assertNotEquals(expected, actual);
    }
}