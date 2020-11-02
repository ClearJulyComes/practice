package com.example.practice.daotests;

import com.example.practice.dao.OrganizationRepository;
import com.example.practice.model.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTests {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void whenFindByIsActive_thenReturnOrganizations(){
        List<Organization> expected = new ArrayList<>();
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        Organization organization1 = new Organization( 2, 0, "Pepsi", "Pepsi-Cola HBC",
                "11114438932", "2222333432", "Moscow, 20" );
        organization.setIsActive(true);
        organization.setPhone("89988");
        organization1.setIsActive(true);
        expected.add(organization);
        expected.add(organization1);

        Assert.assertEquals(Optional.of(expected), organizationRepository.findByIsActive(true));
    }

    @Test
    public void whenFindByIsActive_thenReturnOrganizationsFalse(){
        List<Organization> expected = new ArrayList<>();
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setIsActive(true);
        organization.setPhone("89988");
        expected.add(organization);

        Assert.assertNotEquals(Optional.of(expected), organizationRepository.findByIsActive(true));
    }
}
