package com.example.CSCI1.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CSCI1.project.Demo;
import com.example.CSCI1.project.DemoRepository;
import java.util.*;

@Service
public class DemoServiceImpl implements DemoServiceInterface {
    @Autowired
    DemoRepository demoRepository;
    
    @Override
    public String addNewDemo(Demo demo) {
        Demo result = demoRepository.save(demo);
        return "Saved";
    }

    @Override
    public List<Demo> findAllDemo() {
        return demoRepository.findAll();
    }
    
}
