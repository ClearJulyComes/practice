package com.example.practice.daointerface;

import java.util.List;
import java.util.Optional;

public interface CustomRepository<F, E> {
    List<E> findList(F filter);

    void save(E entity);

    Optional<E> findById(int id);
}
