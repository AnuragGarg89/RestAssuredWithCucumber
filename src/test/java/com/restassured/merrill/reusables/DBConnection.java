package com.restassured.merrill.reusables;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
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

	public void openMongoConnection() {

		try {

			mongoClientURI = new MongoClientURI(dbURI);
			mongoClient = new MongoClient(mongoClientURI);
			mongoDatabase = mongoClient.getDatabase(dbName);
			mongoCollection = mongoDatabase.getCollection(collectionName);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public String getRejectReason(int uniqueTaskId, String fieldName) {

		String str = "";
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		for (int i = 0; i < all.size(); i++) {
			Document doc = all.get(i);
			str = doc.getString(fieldName);
		}
		return str;
	}

	public String getLastAction(int uniqueTaskId, String fieldName) {

		String str = "";
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		for (int i = 0; i < all.size(); i++) {
			Document doc = all.get(i);
			str = doc.getString(fieldName);
		}
		return str;
	}

	public boolean verifyTaskIdExistInDatabase(int uniqueTaskId) {
		long count = 0;
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		count = mongoCollection.count(filter);
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	public long totalNumberOfRecords() {
		long count = 0;
		count = mongoCollection.count();
		return count;

	}

	public boolean isRequeued(int uniqueTaskId) {
		
		boolean requeued = false;
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		for (int i = 0; i < all.size(); i++) {
			Document doc = all.get(i);
			requeued = doc.getBoolean("isRequeued");
		}
		if (requeued) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isRetry(int uniqueTaskId) {
			
			boolean retry = false;
			Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
			List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
			for (int i = 0; i < all.size(); i++) {
				Document doc = all.get(i);
				retry = doc.getBoolean("isRequeued");
			}
			if (retry) {
				return true;
			} else {
				return false;
			}
		}
	
	public String getCurrentState(int uniqueTaskId) {

		String str = "";
		Bson filter = Filters.eq("uniqueTaskId", uniqueTaskId);
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		for (int i = 0; i < all.size(); i++) {
			Document doc = all.get(i);
			str = doc.getString("state");
		}
		return str;
	}	

	public void closeMongoConnection() {
		mongoClient.close();
	}

	/*
	 * public static void main(String []args){ DBConnection dbc = new
	 * DBConnection(); dbc.testMongoConnection(); Object obj =
	 * dbc.getMyData(7660, "rejectReason"); obj.toString(); }
	 */
}
