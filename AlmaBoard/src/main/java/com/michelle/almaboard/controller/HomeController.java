package com.michelle.almaboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {

        return Map.of(
                "projeto", "AlmaBoard API\n", 
                "status", "online\n",
                "documentacao", "/swagger-ui/index.html\n"
        );
    }
}