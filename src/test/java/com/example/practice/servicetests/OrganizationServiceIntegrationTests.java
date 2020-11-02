package com.example.practice.servicetests;

import com.example.practice.dao.OrganizationRepository;
import com.example.practice.model.Organization;
import com.example.practice.service.OrganizationServiceImpl;
import com.example.practice.serviceinterface.OrganizationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationServiceIntegrationTests {
    @TestConfiguration
    static class OrganizationServiceContextConfiguration {

        @Bean
        public OrganizationService organizationService() {
            return new OrganizationServiceImpl();
        }
    }

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void whenUpdate_returnUpdatedObj(){
        Organization organization = new Organization( 1, 0, "OOO", "Tide",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        Organization expectedOrganization = new Organization( 1, 0, "OOO", "Tide",
                "34284438932", "1111333432", "Moscow, 22" );
        expectedOrganization.setPhone("89988");
        expectedOrganization.setIsActive(true);
        organizationService.update(organization);
        Assert.assertEquals(Optional.of(expectedOrganization), organizationRepository.findById(1));
    }
}