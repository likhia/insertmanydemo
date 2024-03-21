package com.ocbc.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.bulk.BulkWriteResult;
import com.ocbc.data.Notification;
import com.ocbc.data.POCColl;
import com.ocbc.demo.repo.NotificationRepo;
import com.ocbc.demo.repo.NotificationRepoImpl;
import com.ocbc.demo.repo.POCRepo;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {
    
    @Autowired
    POCRepo repo;

    @Autowired
    NotificationRepo repo2;

    @Autowired
    MongoTemplate template;

    @Autowired
    NotificationRepoImpl repo3;

    @GetMapping("/doc")
    public java.util.List<POCColl> getDocument(@RequestParam int fld0)  {
        return repo.findByFld0(fld0);
        
    }

    @GetMapping("/insert")
    public String insertNotification() {
        repo3.insertNotification();
        return "OK";
    }

    @GetMapping("/insert2")
    public String insertNotification2() {
        repo3.insertNotification2();
        return "OK";
    }

    /* 
    @GetMapping("/get")
    public Notification getNotification(@RequestParam String id) {
        return repo2.findById(id).get();
    }

    @GetMapping("/get2")
    public void getlist() {

        List<Notification> notifications = repo2.findAll();
        try {
            FileWriter writer = new FileWriter("/Users/jasmine.lim/OCBC/demo/output/random.csv");
            for(Notification msg : notifications) {
                writer.append(msg.getId()+"\n");
            }
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }*/

}
