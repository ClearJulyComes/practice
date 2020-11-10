package com.example.practice.serviceinterface;

import com.example.practice.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CountryService {
    List<Country> getAll();
}
