package com.alterrae.spark.services;

public interface Service {
    void registerRoutes() throws ServiceException;
    void registerFilters() throws ServiceException;
}
