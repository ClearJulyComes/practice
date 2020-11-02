package com.example.practice.servicetests;

import com.example.practice.dao.OrganizationRepository;
import com.example.practice.model.Organization;
import com.example.practice.service.OrganizationServiceImpl;
import com.example.practice.serviceinterface.OrganizationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationServiceTests {
    @TestConfiguration
    static class OrganizationServiceContextConfiguration {

        @Bean
        public OrganizationService organizationService() {
            return new OrganizationServiceImpl();
        }
    }

    @Autowired
    private OrganizationService organizationService;

    @MockBean
    private OrganizationRepository mockOrganizationRepository;

    @Test
    public void whenGetAllActive_returnActiveOrganizations(){
        List<Organization> organizations = new ArrayList<>();
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        organizations.add(organization);
        Mockito.when(mockOrganizationRepository.findByIsActive(true)).thenReturn(Optional.of(organizations));

        Assert.assertEquals(organizations, organizationService.getAllActive());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetAllActive_throwNoSuchElementException(){
        Mockito.when(mockOrganizationRepository.findByIsActive(true)).thenReturn(Optional.empty());

        organizationService.getAllActive();
    }

    @Test(expected = NoSuchElementException.class)
    public void  whenGetOrganization_throwNoSuchElementException(){
        Mockito.when(mockOrganizationRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        organizationService.getOrganization(2);
    }
}