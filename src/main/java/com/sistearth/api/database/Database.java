package com.sistearth.api.database;

import org.sql2o.Sql2o;

public class Database {
    private static Sql2o DATABASE;

    public static Sql2o getDatabase() {
        if (DATABASE == null) {
            DATABASE = new Sql2o("jdbc:mariadb://database:3306/sistearth", "sistearth", "sistearth");
        }
        return DATABASE;
    }
}
