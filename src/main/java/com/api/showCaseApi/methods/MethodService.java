package com.api.showCaseApi.methods;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.api.showCaseApi.model.Schema;
import com.api.showCaseApi.model.SchemaRepository;
import com.api.showCaseApi.services.APIService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class MethodService implements APIService {

    public HashMap<String, Integer> dataMap = new HashMap<String, Integer>();

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
        return data.replaceAll("%20%", ",") + "@#!success";
    }

    public String createMap(String[] input) {
        // update old key counts and insert new keys in the dataMap
        int count = 0, newCount=0;
        for (int i = 0; i < input.length; i++) {
            if(input[i]!=" "){
                if (dataMap.containsKey(input[i])) {
                    count = dataMap.get(input[i]);
                    newCount = count+1;
                    dataMap.replace(input[i], count, newCount);
                } else {
                    dataMap.put(input[i], 1);
                }
            }
        }
        /*
         * dataMap.forEach((k,v)->{ System.out.println("Key: " + k + " Value: " + v);
         * Schema sch = new Schema(k,v); repository.save(sch); });
         */
        return "200@#!Success";
    }

    public HashMap<String, Integer> fetchMap() {
        return dataMap;
    }

    public int getMongoData() {
        // fetch full schema
        // put all the fetched data in a hashmap
        System.out.println("Schema found with findAll():");
        System.out.println("-------------------------------");
        List<Schema> alluser = repository.findAll();
        if(alluser.size()!=0){
            System.out.println("Data found in DB");
            alluser.forEach(item -> {
                dataMap.put(item.getKey(), item.getCount());
            });
        }
        return 200;
    }

    public String saveToMongo(String[] data) {
        return null;
    }
}