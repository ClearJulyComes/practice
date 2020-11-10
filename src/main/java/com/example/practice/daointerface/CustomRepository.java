package com.example.practice.daointerface;

import java.util.List;
import java.util.Optional;

public interface CustomRepository<T, D> {
    Optional<List<D>> findList(T dto);

    void save(D office);

    Optional<D> findById(int id);
}
