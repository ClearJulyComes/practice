package com.example.practice.integrationaltest;

import com.example.practice.view.ResultView;
import com.example.practice.view.organizationview.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(locations = "classpath:test-context.xml")
@AutoConfigureMockMvc
public class OrganizationControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetOrganization_returnOrganization () throws Exception {
        OrganizationIdView expected = new OrganizationIdView(3, "Pepsi", "Pepsi-Cola HBC",
                "11114438932", "2222333432", "Moscow, 20");
        expected.setIsActive(true);
        this.mvc.perform(get("/api/organization/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenGetOrganizations_returnOrganizations () throws Exception {
        OrganizationListFilterDto filterDto = new OrganizationListFilterDto();
        filterDto.setName("Cola");
        Gson gson = new Gson();
        String json = gson.toJson(filterDto);
        List<OrganizationListView> expected = new ArrayList<>();
        OrganizationListView listView1 = new OrganizationListView(1, "Cola", true);
        OrganizationListView listView2 = new OrganizationListView(2, "Cola", false);
        expected.add(listView1);
        expected.add(listView2);
        this.mvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenSaveOrganization_returnSuccess () throws Exception {
        OrganizationSaveDto saveDto = new OrganizationSaveDto("Test organization", "Test HBC",
                "12", "12", "Moscow, 20");
        Gson gson = new Gson();
        String json = gson.toJson(saveDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenUpdateOrganization_returnSuccess () throws Exception {
        OrganizationUpdateDto updateDto = new OrganizationUpdateDto(2, "Test update", "Test HBC",
                "12345", "1221", "Moscow, 233");
        Gson gson = new Gson();
        String json = gson.toJson(updateDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    private <T> T asParsedJson(Object obj) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(obj);
        return JsonPath.read(json, "$");
    }
}
