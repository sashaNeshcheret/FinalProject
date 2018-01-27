package ua.simpleproject.dao;


import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;

import java.sql.*;
import java.util.Objects;

public interface DAOProduct {

    Product readById(int id) throws DAOException;
    Product read (int code) throws DAOException;
    Product read(String name) throws DAOException;
    void create(Product product) throws DAOException;
}
