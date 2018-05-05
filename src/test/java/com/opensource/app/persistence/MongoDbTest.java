package com.opensource.app.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.opensource.app.models.Weight;

public class MongoDbTest {
	MongoClient mongoClient;
	DB dataBase;
	
	@Before
	public void setupTest() throws UnknownHostException {
		System.out.println("soy el metodo SetupTest");
		mongoClient = new MongoClient();
		dataBase = mongoClient.getDB("java-junit");
	}
	
	@Test
	public void writeIntoDb() {
		System.out.println("soy el metodo que escribe en la base de datos");
		DBObject weight = new BasicDBObject("_id","123").append("value",63).append("unit", "kg");
		DBObject result = new BasicDBObject();
		dataBase.getCollection("Weights").insert(weight);
		result = dataBase.getCollection("Weights").findOne();
		assertNotNull(result);
		assertEquals("123",result.get("_id").toString());
		assertEquals("63",result.get("value").toString());
		assertEquals("kg",result.get("unit").toString());
	}
	
	@Test
	public void readFromDb() {
		System.out.println("Soy el método que lee de la base de datos.");
		List<DBObject> objectList = new ArrayList<DBObject>();
		DBObject weight = new BasicDBObject("_id","1").append("value", 80).append("unit", "kg");
		DBObject weight2 = new BasicDBObject("_id","2").append("value", 45).append("unit", "lb");
		objectList.add(weight);
		objectList.add(weight2);
		dataBase.getCollection("Weights").insert(objectList);
		
	}
	
	@After
	public void afterTest() {
		System.out.println("Soy el metodo afterTest.");
		dataBase.getCollection("Weights").drop();
		mongoClient.close();
	}

}
