package com.example.practice.controller;

import com.example.practice.view.organizationview.*;
import com.example.practice.serviceinterface.OrganizationService;
import com.example.practice.view.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Rest controller for handling Http requests for organization management
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Constructor with dependency of {@link OrganizationService}
     *
     * @param organizationService injected bean
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Get list of organizations which satisfied to filter
     *
     * @param dto filter
     * @return list of organization's dto
     */
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public List<OrganizationListView> getOrgList(@RequestBody @Valid OrganizationListFilterDto dto) {
        return organizationService.getAllActive(dto);
    }

    /**
     * Get organization by id
     *
     * @param id unique id of searching organization
     * @return organization dto
     */
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public OrganizationIdView getOrg(@PathVariable int id) {
        return organizationService.getOrganization(id);
    }

    /**
     * Update organization data
     *
     * @param dto new organization params
     * @return result status
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResultView updateOrg(@RequestBody @Valid OrganizationUpdateDto dto) {
        organizationService.update(dto);
        return new ResultView("Success");
    }

    /**
     * Save new organization
     *
     * @param dto params of new organization
     * @return result status
     */
    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public ResultView saveOrg(@RequestBody @Valid OrganizationSaveDto dto) {
        organizationService.save(dto);
        return new ResultView("Success");
    }
}
