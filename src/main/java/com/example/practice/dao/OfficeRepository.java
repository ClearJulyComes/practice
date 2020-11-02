package com.example.practice.dao;

import com.example.practice.model.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends CrudRepository<Office, Integer> {
    Optional<List<Office>> findByIsActive(boolean isActive);
}
