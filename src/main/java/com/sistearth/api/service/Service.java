package com.sistearth.api.service;

import org.sql2o.Sql2o;

public interface Service {
    void registerRoutes(Sql2o database) throws ServiceException;
}
