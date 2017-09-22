package com.restassured.merrill.reusables;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DBConnection extends BaseClass {

	public static MongoClient mongoClient;
	public MongoDatabase mongoDatabase;
	public MongoCollection<Document> mongoCollection;
	public FindIterable<Document> iterable;
	public MongoClientURI mongoClientURI;
	public String host;
	public int port;
	public String userName;
	public String passWord;
	public String dbName;
	public String collectionName;
	public String dbURI;

	public DBConnection() {

		host = config.getProperty("Host");
		port = Integer.parseInt(config.getProperty("Port"));
		userName = config.getProperty("UserName");
		passWord = config.getProperty("Password");
		dbName = config.getProperty("DatabaseName");
		collectionName = config.getProperty("Collection");
		dbURI = "mongodb://" + userName + ":" + passWord + "@" + host + ":" + port;
	}

	public DBConnection(String databaseName, String collection) {

		host = config.getProperty("Host");
		port = Integer.parseInt(config.getProperty("Port"));
		userName = config.getProperty("UserName");
		passWord = config.getProperty("Password");
		dbName = databaseName;
		collectionName = collection;
		dbURI = "mongodb://" + userName + ":" + passWord + "@" + host + ":" + port;
	}

	public DBConnection(String host, int port, String userName, String passWord, String databaseName,
			String collection) {

		this.host = host;
		this.port = port;
		this.userName = userName;
		this.passWord = passWord;
		dbName = databaseName;
		collectionName = collection;
		dbURI = "mongodb://" + userName + ":" + passWord + "@" + host + ":" + port;
	}

	public void testMongoConnection() {

		try {

			mongoClientURI = new MongoClientURI(dbURI);
			mongoClient = new MongoClient(mongoClientURI);
			mongoDatabase = mongoClient.getDatabase(dbName);
			mongoCollection = mongoDatabase.getCollection(collectionName);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

	}

	public Object getMyData(Object uniqueTaskId, Object key) {

		Object obj = new Object();
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		 for (int i = 0; i < all.size(); i++) {
			 Document doc = all.get(i);
			 obj = doc.get(key);
		 }
		
		MongoCursor<Document> mongoCursor = iterable.iterator();	
		while (mongoCursor.hasNext()) {
			Document doc = mongoCursor.next();
			try {
					obj = doc.get(key);				
				/*if ((doc.get("uniqueTaskId") != null) && (doc.get("uniqueTaskId").equals(uniqueTaskId))) {
					obj = doc.get(key);
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		mongoClient.close();
		return obj;
	}
	
	public static void main(String []args){
		DBConnection dbc = new DBConnection();
		dbc.testMongoConnection();
		Object obj = dbc.getMyData(4002, "rejectReason");
		obj.toString();
	}
}
