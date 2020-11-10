package com.example.practice.servicetests;

import com.example.practice.daointerface.OrganizationRepository;
import com.example.practice.view.organizationview.OrganizationIdView;
import com.example.practice.view.organizationview.OrganizationListFilterDto;
import com.example.practice.view.organizationview.OrganizationListView;
import com.example.practice.model.Organization;
import com.example.practice.model.mapper.CustomMapperFactory;
import com.example.practice.model.mapper.OrganizationMapperFacadeImpl;
import com.example.practice.service.OrganizationServiceImpl;
import com.example.practice.serviceinterface.OrganizationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomMapperFactory.class, OrganizationMapperFacadeImpl.class,
        OrganizationServiceImpl.class})
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
    private OrganizationRepository organizationRepository;

    @Test
    public void whenGetAllActive_returnActiveOrganizations(){
        List<OrganizationListView> expected = new ArrayList<>();
        OrganizationListView dto1 = new OrganizationListView( 1, "Cola", false);
        OrganizationListView dto2 = new OrganizationListView( 2, "Cola", true);
        expected.add(dto1);
        expected.add(dto2);
        List<Organization> organizations = new ArrayList<>();
        Organization organization1 = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        organization1.setIsActive(false);
        organizations.add(organization1);
        Organization organization2 = new Organization( 2, 0, "Cola", "Coca-Cola HBC",
                "34284438911", "1111333432", "Moscow, 22" );
        organization2.setIsActive(true);
        organizations.add(organization2);
        Mockito.when(organizationRepository.findList(Mockito.any())).thenReturn(Optional.of(organizations));

        Assert.assertEquals(expected, organizationService.getAllActive(new OrganizationListFilterDto("Cola")));
    }

    @Test
    public void  whenGetOrganization_returnOrganizationDto(){
        OrganizationIdView expected = new OrganizationIdView( 1, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22");
        Organization organization = new Organization( 1, 0, "Cola", "Coca-Cola",
                "34284438932", "1111333432", "Moscow, 22" );
        Mockito.when(organizationRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(organization));
        OrganizationIdView actual = organizationService.getOrganization(1);
        Assert.assertEquals(expected, actual);

        expected.setIsActive(true);
        Assert.assertNotEquals(expected, actual);

        expected.setPhone("89988");
        organization.setIsActive(true);
        organization.setPhone("89988");
        actual = organizationService.getOrganization(1);
        Assert.assertEquals(expected, actual);
    }
}