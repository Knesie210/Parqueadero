package com.espe.parqueadero.Models;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static MongoDatabase database;
    private final MongoClient mongoClient;

    public MongoDB() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("parqueadero");
    }

    public static MongoCollection<Document> getVehiculoCollection() {
        if (database == null) {
            throw new IllegalStateException("MongoDB database is not initialized.");
        }
        return database.getCollection("vehiculos");
    }

    public void close() {
        mongoClient.close();
    }
}
