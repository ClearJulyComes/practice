package com.example.practice.controller;

import com.example.practice.view.ResultView;
import com.example.practice.view.userview.*;
import com.example.practice.serviceinterface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for handling http requests for user management
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserInfoService userInfoService;

    /**
     * Constructor with dependency of {@link UserInfoService}
     *
     * @param userInfoService injected bean
     */
    @Autowired
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * Get list of users by filter
     *
     * @param dto filter params
     * @return list of users satisfied to filter
     */
    @PostMapping("/list")
    public List<UserListView> getUserList(@RequestBody @Valid UserListFilterDto dto) {
        return userInfoService.findList(dto);
    }

    /**
     * Get user by id
     *
     * @param id id of searching user
     * @return user dto
     */
    @GetMapping("/{id}")
    public UserIdView getUser(@PathVariable int id) {
        return userInfoService.findById(id);
    }

    /**
     * Update user info
     *
     * @param dto new data of user
     * @return result status
     */
    @PostMapping("/update")
    public ResultView updateUser(@RequestBody @Valid UserUpdateDto dto) {
        userInfoService.update(dto);
        return new ResultView("Success");
    }

    /**
     * Save new user
     *
     * @param dto new user info
     * @return result status
     */
    @PostMapping("/save")
    public ResultView saveUser(@RequestBody @Valid UserSaveDto dto) {
        userInfoService.save(dto);
        return new ResultView("Success");
    }
}
