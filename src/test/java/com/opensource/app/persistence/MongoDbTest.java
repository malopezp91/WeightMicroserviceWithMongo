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
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.opensource.app.models.Weight;

public class MongoDbTest {
	MongoClient mongoClient;
	DB dataBase;
	private static final String DATABASE_NAME="java-junit";
	private static final String COLLECTION_NAME = "Weights";
	
	@Before
	public void setupTest() throws UnknownHostException {
		System.out.println("soy el metodo SetupTest");
		mongoClient = new MongoClient();
		dataBase = mongoClient.getDB(DATABASE_NAME);
	}
	
	@Test
	public void writeIntoDb() {
		
		DBObject weight = new BasicDBObject("_id","123").append("value",63).append("unit", "kg");
		DBObject result = new BasicDBObject();
		dataBase.getCollection(COLLECTION_NAME).insert(weight);
		result = dataBase.getCollection(COLLECTION_NAME).findOne();
		assertNotNull(result);
		assertEquals("123",result.get("_id").toString());
		assertEquals("63",result.get("value").toString());
		assertEquals("kg",result.get("unit").toString());
	}
	
	@Test
	public void readFromDb() {
	
		List<DBObject> objectList = new ArrayList<DBObject>();
		DBObject weight = new BasicDBObject("_id","1").append("value", 80).append("unit", "kg");
		DBObject weight2 = new BasicDBObject("_id","2").append("value", 45).append("unit", "lb");
		DBObject query = new BasicDBObject("_id","2");
		objectList.add(weight);
		objectList.add(weight2);
		DBCollection collection = dataBase.getCollection(COLLECTION_NAME);
		collection.insert(objectList);
		
		
		DBCursor cursor = collection.find(query);
		List<DBObject> result = new ArrayList<DBObject>();
		
		while(cursor.hasNext()) {
			result.add(cursor.next());		
		}
		
		for(DBObject element: result) {
			System.out.println(element.toString());
			assertNotNull(element);
			assertEquals("2", element.get("_id").toString());
			assertEquals("lb",element.get("unit").toString());
		}
		cursor.close();
	}
	
	@Test
	public void deleteDocument() {
		DBObject weight = new BasicDBObject("_id","1").append("value", 90).append("unit", "kg");
		DBCollection collection = dataBase.getCollection(COLLECTION_NAME);
		collection.insert(weight);
		
		DBObject documentToDelete = new BasicDBObject("_id","1");
		collection.remove(documentToDelete);
	}
	
	@After
	public void afterTest() {
		System.out.println("Soy el metodo afterTest.");
		dataBase.getCollection("Weights").drop();
		mongoClient.close();
	}

}
