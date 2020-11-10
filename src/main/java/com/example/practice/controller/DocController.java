package com.example.practice.controller;

import com.example.practice.model.Doc;
import com.example.practice.serviceinterface.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller for
 */
@RestController
public class DocController {

    private final DocService docService;

    /**
     * Constructor with dependency of {@link DocService}
     * @param docService injected bean
     */
    @Autowired
    public DocController(DocService docService) {
        this.docService = docService;
    }

    /**
     * Get all docs from DB
     * @return list of docs
     */
    @GetMapping("api/docs")
    public List<Doc> getDocs(){
        return docService.getAll();
    }
}
