package com.example.practice.daotests;

import com.example.practice.daointerface.CustomRepository;
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

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTests {

    @TestConfiguration
    static class OrganizationServiceContextConfiguration {

        @Bean
        public CustomRepository<OrganizationListFilterDto, Organization> organizationRepository(
                EntityManager entityManager) {
            return new OrganizationRepositoryImpl(entityManager);
        }
    }

    @Autowired
    private CustomRepository<OrganizationListFilterDto, Organization> organizationRepository;

    @Test
    public void whenFindList_thenReturnOrganizations() {
        List<Organization> expected = new ArrayList<>();
        Organization organization1 = new Organization(1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22");
        organization1.setIsActive(true);
        organization1.setPhone("89988");
        Organization organization2 = new Organization(2, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22");
        organization2.setIsActive(false);
        expected.add(organization1);
        expected.add(organization2);
        OrganizationListFilterDto dto = new OrganizationListFilterDto("Cola");
        List<Organization> actual = organizationRepository.findList(dto);

        Assert.assertEquals(expected, actual);
    }
}
