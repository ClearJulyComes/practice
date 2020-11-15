package com.example.practice.integrationaltest;

import com.example.practice.view.ResultView;
import com.example.practice.view.officeview.*;
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
@SpringBootTest
@AutoConfigureMockMvc
public class OfficeControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetOffice_returnOffice () throws Exception {
        OfficeIdView expected = new OfficeIdView(1, "HR", null, "Moscow, 22", true);
        this.mvc.perform(get("/api/office/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenGetOffices_returnOffices () throws Exception {
        OfficeListFilterDto filterDto = new OfficeListFilterDto(2, "Testers");
        Gson gson = new Gson();
        String json = gson.toJson(filterDto);
        List<OfficeListView> expected = new ArrayList<>();
        OfficeListView listView = new OfficeListView(3, "Testers", null);
        expected.add(listView);
        this.mvc.perform(post("/api/office/list").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenSaveOffice_returnSuccess () throws Exception {
        OfficeSaveDto saveDto = new OfficeSaveDto(2, "TestOffice");
        Gson gson = new Gson();
        String json = gson.toJson(saveDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/office/save").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenUpdateOffice_returnSuccess () throws Exception {
        OfficeUpdateDto updateDto = new OfficeUpdateDto(4, "Updated office", "Somewhere");
        Gson gson = new Gson();
        String json = gson.toJson(updateDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/office/update").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    private <T> T asParsedJson(Object obj) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(obj);
        return JsonPath.read(json, "$");
    }
}
