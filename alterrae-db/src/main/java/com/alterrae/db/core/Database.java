package com.alterrae.db.core;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Database {

    private static Datastore dataStore = null;
    private static final Morphia morphia = new Morphia();

    public static void initDB() {
        morphia.mapPackage("com.alterrae.db.api.entity");
        dataStore = morphia.createDatastore(new MongoClient("database"), "alterrae");
        dataStore.ensureIndexes();
    }

    public static Datastore getDatastore() {
        return dataStore;
    }

    public static Morphia getMorphia() {
        return morphia;
    }
}
