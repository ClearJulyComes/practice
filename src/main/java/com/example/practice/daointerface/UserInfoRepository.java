package com.example.practice.daointerface;

import com.example.practice.view.userview.UserListFilterDto;
import com.example.practice.model.UserInfo;

import java.util.List;
import java.util.Optional;

/**
 * User repository interface
 */
public interface UserInfoRepository {
    Optional<List<UserInfo>> findList(UserListFilterDto dto);
    void save(UserInfo dto);
    Optional<UserInfo> findById(int id);
}
