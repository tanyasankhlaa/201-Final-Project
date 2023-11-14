package com.example.CSCI1.project.service;

import java.util.List;

import com.example.CSCI1.project.Demo;

public interface DemoServiceInterface {
    public String addNewDemo(Demo demo);
    public List<Demo> findAllDemo(); 
}
