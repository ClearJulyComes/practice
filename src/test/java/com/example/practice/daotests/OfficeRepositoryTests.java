package com.example.practice.daotests;

import com.example.practice.dao.OfficeRepository;
import com.example.practice.model.Office;
import com.example.practice.model.Organization;
import org.junit.Assert;
import org.junit.Before;
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
public class OfficeRepositoryTests {

    @Autowired
    private OfficeRepository officeRepository;

    private List<Office> expected;

    @Before
    public void setUp() {
        expected = new ArrayList<>();
    }

    @Test
    public void whenFindByIsActive_thenReturnOffices(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setActive(true);
        organization.setPhone("89988");
        Office office = new Office( 1, organization);
        office.setActive(true);
        office.setName("HR");
        office.setVersion(0);
        office.setAddress("Moscow, 22");
        expected.add(office);
        Office office1 = new Office( 2, organization);
        office1.setActive(true);
        office1.setName("Developers");
        office1.setVersion(0);
        office1.setAddress("Moscow, 22");
        expected.add(office1);

        Assert.assertEquals(Optional.of(expected), officeRepository.findByIsActive(true));
    }

    @Test
    public void whenFindByIsActive_thenReturnOfficesFalse(){
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization.setActive(true);
        organization.setPhone("89988");
        Office office = new Office( 1, organization);
        office.setActive(true);
        office.setName("HR");
        expected.add(office);

        Assert.assertNotEquals(Optional.of(expected), officeRepository.findByIsActive(true));
    }
}
