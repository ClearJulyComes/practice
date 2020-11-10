package com.example.practice.serviceinterface;


import com.example.practice.view.userview.*;

import java.util.List;

public interface UserInfoService {
    List<UserListView> findList(UserListFilterDto dto);
    UserIdView findById(int id);
    void save(UserSaveDto dto);
    void update(UserUpdateDto dto);
}
