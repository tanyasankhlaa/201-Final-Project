package com.example.CSCI1.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CSCI1.project.Demo;
import com.example.CSCI1.project.service.DemoServiceImpl;
import java.util.*;

@RestController
@RequestMapping("/demo")
@CrossOrigin
public class DemoController {
    @Autowired
    DemoServiceImpl demoServiceImpl;

    @PostMapping(path="/add", consumes = "application/json") // ,,Map ONLY POST Requests
    public @ResponseBody String addNewDemo (@RequestBody Demo demo) {
        //Demo demo = new Demo(title,content);
        return demoServiceImpl.addNewDemo(demo);
    }


    @GetMapping
    public List<Demo> findAllDemo(){
        return demoServiceImpl.findAllDemo();
    }
}
