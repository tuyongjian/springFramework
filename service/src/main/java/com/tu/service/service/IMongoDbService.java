package com.tu.service.service;

import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * Created by len on 2019/3/19.
 */
public interface IMongoDbService {


     void insert();


    void createAndInsert();

     FindIterable<Document> query();


     void update();

     void delete();





}
