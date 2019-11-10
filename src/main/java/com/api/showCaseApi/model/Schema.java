package com.api.showCaseApi.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DB_showCaseAPI")
public class Schema {
    
    private String key;
    private int count;

    public String getKey() {
        return key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private Schema(String key, int count){
        this.key = key;
        this.count = count;
    }

}