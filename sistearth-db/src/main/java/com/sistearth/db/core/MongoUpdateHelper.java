package com.sistearth.db.core;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.Map;

public class MongoUpdateHelper<T> {

    private final Datastore ds;
    private final Class<T> clazz;

    public MongoUpdateHelper(Datastore ds, Class<T> clazz) {
        this.ds = ds;
        this.clazz = clazz;
    }

    public UpdateOperations<T> createUpdateOperations(Map<String, Object> data) {
        UpdateOperations<T> ops = ds.createUpdateOperations(clazz);
        data.forEach(ops::set);
        return ops;
    }
}
