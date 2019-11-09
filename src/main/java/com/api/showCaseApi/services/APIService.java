package com.api.showCaseApi.services;

import org.springframework.beans.factory.annotation.Autowired;

public interface APIService{
    @Autowired
    public String fetchKeywords(String data);
}