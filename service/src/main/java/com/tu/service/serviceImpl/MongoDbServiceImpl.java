package com.tu.service.serviceImpl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.tu.service.service.IMongoDbService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by len on 2019/3/19.
 */
@Service
public class MongoDbServiceImpl implements IMongoDbService {

    private Logger logger = LoggerFactory.getLogger(MongoDbServiceImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("runoob");
        Document document = new Document("title", "MongoDB").
        append("description", "database").
        append("likes", 100).
        append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        logger.info("MongoDB插入成功-----------------");
    }

    public void createAndInsert() {

        MongoCollection<Document> collection = mongoTemplate.getCollection("runoob");

        logger.info("runoob-test collection chooise is success---");

        if(collection ==null){
            mongoTemplate.createCollection("runoob");
            logger.info("runoob-test create is success--------------");
        }else{

            Document document = new Document("title", "MongoDB1111").
                    append("description", "database").
                    append("likes", 100).
                    append("by", "Fly");
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            logger.info("MongoDB插入成功-----------------");
        }

    }

    public FindIterable<Document> query() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("runoob");
        FindIterable<Document> findIterable = collection.find();
        return findIterable;
    }

    public void update() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("runoob");
        collection.updateMany(Filters.eq("likes",100),new Document("$set",new Document("likes",200)));
    }

    public void delete() {
        MongoCollection<Document> collection = mongoTemplate.getCollection("runoob");

        //删除符合条件的第一个文档
        collection.deleteOne(Filters.eq("likes", 200));

        //删除所有符合条件的文档
       // collection.deleteMany (Filters.eq("likes", 200));
    }

}
