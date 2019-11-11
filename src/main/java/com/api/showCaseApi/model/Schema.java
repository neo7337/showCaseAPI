package com.api.showCaseApi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

@Document(collection = "DB_showCaseAPI")
public class Schema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    public String id;
    @Column(name = "Key")
    private String key;
    @Column(name = "Count")
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

    public Schema(String key, int count){
        this.key = key;
        this.count = count;
    }

}