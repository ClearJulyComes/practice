package com.example.practice.mappertests;

import com.example.practice.dto.OrganizationDto;
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
public class OrganizationMapperTests {

    @Autowired
    @Qualifier("organizationMapperFactoryBean")
    private MapperFactory mapperFactory;

    @Test
    public void whenMap_returnMappedObject(){
        Organization organization = new Organization( 2, 0, "Pepsi", "Pepsi-Cola HBC",
                "11114438932", "2222333432", "Moscow, 20" );
        organization.setIsActive(true);
        organization.setPhone("89988");
        OrganizationDto expected = new OrganizationDto( 2, "Pepsi", "Pepsi-Cola HBC",
                "11114438932", "2222333432", "Moscow, 20", "89988", true);
        OrganizationDto actual = mapperFactory.getMapperFacade().map(organization, OrganizationDto.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenMap_returnMappedObjectFalse(){
        Organization organization = new Organization( 2, 0, "Pepsi", "Pepsi-Cola HBC",
                "11114438932", "2222333432", "Moscow, 20" );
        organization.setIsActive(true);
        organization.setPhone("89988");
        OrganizationDto expected = new OrganizationDto( 2, "Coca-Cola", "HeyNaNaNa",
                "11114438932", "2222333432", "Moscow, 20", "89988", true);
        OrganizationDto actual = mapperFactory.getMapperFacade().map(organization, OrganizationDto.class);
        Assert.assertNotEquals(expected, actual);
    }
}
