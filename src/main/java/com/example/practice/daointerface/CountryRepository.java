package com.example.practice.daointerface;

import com.example.practice.model.Country;

import java.util.List;

/**
 * Country repository interface
 */
public interface CountryRepository {
    /**
     * Find all countries
     *
     * @return list of countries
     */
    List<Country> getList();

    /**
     * Find country by code
     *
     * @param code country unique code
     * @return country entity
     */
    Country findByCode(int code);
}
