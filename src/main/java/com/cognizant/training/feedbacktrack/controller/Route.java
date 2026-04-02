package com.cognizant.training.feedbacktrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Route {

    @GetMapping("/path")
    public String path(){
        return "This is the first route";
    }
}
