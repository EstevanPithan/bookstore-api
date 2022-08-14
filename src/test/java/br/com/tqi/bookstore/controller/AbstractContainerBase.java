package br.com.tqi.bookstore.controller;


import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractContainerBase {

    static final MySQLContainer MYSQL_CONTAINER;

    static {
        MYSQL_CONTAINER = new MySQLContainer("mysql");
        MYSQL_CONTAINER.start();
        System.setProperty("spring.datasource.url", MYSQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.username", MYSQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", MYSQL_CONTAINER.getPassword());

    }
}
