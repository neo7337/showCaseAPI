package com.api.showCaseApi.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchemaRepository extends MongoRepository<Schema, String> {
    public List<Schema> findByKey(String key);
}