package com.example.practice.integrationaltest;

import com.example.practice.view.ResultView;
import com.example.practice.view.userview.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetUser_returnUser () throws Exception {
        String localDateStr = "2011-11-22";
        UserIdView expected = new UserIdView(1, "Viktor", null, 8, "Albania",
                "Свидетельство о рождении", "1111", localDateStr);
        expected.setPosition("Director");
        this.mvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenGetUsers_returnUsers () throws Exception {
        UserListFilterDto filterDto = new UserListFilterDto(1, "Viktor");
        Gson gson = new Gson();
        String json = gson.toJson(filterDto);
        List<UserListView> expected = new ArrayList<>();
        UserListView listView = new UserListView(1, "Viktor", "Director");
        expected.add(listView);
        this.mvc.perform(post("/api/user/list").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenSaveUser_returnSuccess () throws Exception {
        UserSaveDto saveDto = new UserSaveDto(2, "Test", "Lol", true);
        Gson gson = new Gson();
        String json = gson.toJson(saveDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/user/save").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    @Test
    public void whenUpdateUser_returnSuccess () throws Exception {
        UserUpdateDto updateDto = new UserUpdateDto(4, "Test", "LolPosition");
        Gson gson = new Gson();
        String json = gson.toJson(updateDto);
        ResultView expected = new ResultView("Success");
        this.mvc.perform(post("/api/user/update").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.data").value(equalTo(asParsedJson(expected))));
    }

    private <T> T asParsedJson(Object obj) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(obj);
        return JsonPath.read(json, "$");
    }
}
