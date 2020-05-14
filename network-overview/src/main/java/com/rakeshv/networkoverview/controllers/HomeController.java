package com.rakeshv.networkoverview.controllers;

import com.rakeshv.networkoverview.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    GraphService graphService;

    @GetMapping("/graph")
    public Map<String, Object> graph() {
        return graphService.graph();
    }
}
