package com.example.practice.daointerface;

import com.example.practice.model.Doc;

import java.util.List;

/**
 * Doc repository interface
 */
public interface DocRepository {
    List<Doc> getList();

    Doc findByCode(int code);

    Doc findByName(String name);
}
