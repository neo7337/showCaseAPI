package com.api.showCaseApi.methods;

import java.util.HashMap;
import java.util.List;

import com.api.showCaseApi.keywordExtract.algorithm.TextRank;
import com.api.showCaseApi.model.Schema;
import com.api.showCaseApi.model.SchemaRepository;
import com.api.showCaseApi.services.APIService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class MethodService implements APIService {

    public HashMap<String, Integer> dataMap = new HashMap<String, Integer>();

    @Autowired
    private SchemaRepository repository;

    public String fetchKeywords(String data) {
        StringBuffer resultSet = new StringBuffer("");
        try {
            List<String> resultKeywords = TextRank.getkeyword(data);
            for (String str : resultKeywords) {
                resultSet.append(str+",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet.toString() + "@#!success";
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