package com.example.CSCI1.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.CSCI1.project.DemoRepository;

@RestController
public class DemoController {
    @Autowired
    DemoRepository demoRespository;
}
