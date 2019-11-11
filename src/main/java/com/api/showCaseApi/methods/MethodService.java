package com.api.showCaseApi.methods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.api.showCaseApi.model.Schema;
import com.api.showCaseApi.model.SchemaRepository;
import com.api.showCaseApi.services.APIService;

import org.springframework.beans.factory.annotation.Autowired;

//import com.api.showCaseApi.MongoConfig.Mongo;

public abstract class MethodService implements APIService {

    @Autowired
    private SchemaRepository repository;

    public String fetchKeywords(String data) {
        try {
            System.out.println("data " + data);
            Process p = Runtime.getRuntime().exec("python KeyFetch.py");
            p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer buf = new StringBuffer("");
            String line = null;
            System.out.println(in.readLine());
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                buf.append(line);
            }
            System.out.println(buf.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "interesting,inspiring@#!success";
    }

    public String saveToMongo(String[] input) {
        //update old key counts and insert new keys in the database
        return "200@#!Success";
    }

    public int getMongoData() {
        // fetch full schema
        // put all the fetched data in a hashmap
        System.out.println("Schema found with findAll():");
        System.out.println("-------------------------------");
        List<Schema> alluser = repository.findAll();
		alluser.forEach(item -> System.out.println(item));
        return 200;
    }
}