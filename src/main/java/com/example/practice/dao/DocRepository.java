package com.example.practice.dao;

import com.example.practice.model.Doc;
import org.springframework.data.repository.CrudRepository;

public interface DocRepository extends CrudRepository<Doc, Integer> {
}
