package com.example.practice.controller;

import com.example.practice.view.officeview.*;
import com.example.practice.serviceinterface.OfficeService;
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
 * Rest controller for handling office's http requests
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {
    private final OfficeService officeService;

    /**
     * Constructor with dependency of {@link OfficeService}
     *
     * @param officeService injected bean
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Get office data by id
     *
     * @param id unique id for office finding
     * @return office dto
     */
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public OfficeIdView getOffice(@PathVariable int id) {
        return officeService.getOffice(id);
    }

    /**
     * Get office data by filter
     *
     * @param dto for filtering offices
     * @return list of offices which satisfied for filter
     */
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public List<OfficeListView> getOfficeList(@RequestBody @Valid OfficeListFilterDto dto) {
        return officeService.getAllActive(dto);
    }

    /**
     * Update office data
     *
     * @param dto new data for updating
     * @return result status
     */
    @PostMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResultView updateOffice(@RequestBody @Valid OfficeUpdateDto dto) {
        officeService.update(dto);
        return new ResultView("Success");
    }

    /**
     * Save office data
     *
     * @param dto new office data for saving
     * @return result status
     */
    @PostMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public ResultView saveOffice(@RequestBody @Valid OfficeSaveDto dto) {
        officeService.save(dto);
        return new ResultView("Success");
    }
}
