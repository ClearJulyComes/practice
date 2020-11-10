package com.example.practice.daotests;

import com.example.practice.daointerface.OrganizationRepository;
import com.example.practice.dao.OrganizationRepositoryImpl;
import com.example.practice.view.organizationview.OrganizationListFilterDto;
import com.example.practice.model.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTests {

    @TestConfiguration
    static class OrganizationServiceContextConfiguration {

        @Bean
        public OrganizationRepository organizationRepository() {
            return new OrganizationRepositoryImpl();
        }
    }

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void whenFindList_thenReturnOrganizations(){
        List<Organization> organizations = new ArrayList<>();
        Organization organization1 = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization1.setIsActive(true);
        organization1.setPhone("89988");
        Organization organization2 = new Organization( 2, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization2.setIsActive(false);
        organizations.add(organization1);
        organizations.add(organization2);
        Optional<List<Organization>> expected = Optional.of(organizations);
        OrganizationListFilterDto dto = new OrganizationListFilterDto("Cola");
        Optional<List<Organization>> actual = organizationRepository.findList(dto);

        Assert.assertEquals(expected, actual);
    }
}
