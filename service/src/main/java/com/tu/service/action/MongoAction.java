package com.tu.service.action;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.tu.service.service.IMongoDbService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by len on 2019/3/19.
 */

@Controller
@RequestMapping(value = "mongo")
public class MongoAction {

    private Logger logger = LoggerFactory.getLogger(MongoAction.class);

    @Autowired
    private IMongoDbService mongoDbService;


    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST)
    public Object test(){
        this.mongoDbService.insert();
        return true;
    }


    @ResponseBody
    @RequestMapping(value = "createAndInsert",method = RequestMethod.POST)
    public Object createAndInsert(){
        this.mongoDbService.createAndInsert();
        return true;
    }


    @ResponseBody
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Object query(){
        FindIterable<Document> findIterable = this.mongoDbService.query();

        MongoCursor<Document> mongoCursor = findIterable.iterator();

        while(mongoCursor.hasNext()){
            Document document = mongoCursor.next();
            logger.info("------------"+document.toJson());
            logger.info("------------"+document.get("title"));

        }

        return true;
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object update(){
        this.mongoDbService.update();
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public Object delete(){
        this.mongoDbService.delete();
        return true;
    }


}
