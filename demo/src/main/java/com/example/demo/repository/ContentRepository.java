package com.example.demo.repository;

import com.example.demo.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ContentRepository extends MongoRepository<Content,String> {

}
