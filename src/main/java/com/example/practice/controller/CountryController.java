package com.example.practice.controller;

import com.example.practice.model.Country;
import com.example.practice.serviceinterface.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for countries requests.
 */
@RestController
public class CountryController {


    private final CountryService countryService;

    /**
     * Constructor with dependency injection of {@link CountryService}
     * @param countryService injected bean
     */
    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    /**
     * Get all countries
     * @return list of countries
     */
    @GetMapping("/api/countries")
    public List<Country> getCountries(){
        return countryService.getAll();
    }
}
