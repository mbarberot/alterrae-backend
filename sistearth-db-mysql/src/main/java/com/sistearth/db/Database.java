package com.sistearth.db;

import org.sql2o.Sql2o;

public class Database {
    private static Sql2o sql2o;

    private Database() {
    }

    public static Sql2o getDatabase() {
        if (sql2o == null) {
            sql2o = new Sql2o("jdbc:mariadb://database:3306/sistearth", "sistearth", "sistearth");
        }
        return sql2o;
    }
}
