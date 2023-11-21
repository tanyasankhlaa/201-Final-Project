package com.example.CSCI1.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {

    // custom query
    //Add certain functionalities like fetching all 
    //records, saving, updating, deleting, etc
}
