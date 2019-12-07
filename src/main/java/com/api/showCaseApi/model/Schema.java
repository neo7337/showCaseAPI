package com.api.showCaseApi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Document(collection = "DB_showCaseAPI")
public class Schema implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    String id;
    
    @Column(name = "key")
    String key;

    @Column(name = "count")
    int count;

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