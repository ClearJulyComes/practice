package com.example.practice.serviceinterface;


import com.example.practice.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    List<UserInfo> getAllActive();
    UserInfo getOrganization(int id);
    void update(UserInfo userInfo);
    void save(UserInfo userInfo);
}
