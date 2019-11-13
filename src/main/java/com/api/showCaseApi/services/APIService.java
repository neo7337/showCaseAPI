package com.api.showCaseApi.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

public interface APIService{
    @Autowired
    public String fetchKeywords(String data);

    @Autowired
    public String saveToMongo(String[] data);

    @Autowired
    public int getMongoData();

    @Autowired 
    public String createMap(String[] data);

    @Autowired
    public HashMap<String, Integer> fetchMap();
}