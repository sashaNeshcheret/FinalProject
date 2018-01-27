package ua.simpleproject.dao;


import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;

import java.math.BigDecimal;
import java.util.List;

public interface DAOStock {

    Stock read(Product product) throws DAOException, LogicException;
    List<Stock> read(int start, int end) throws DAOException;
    void create(Product product, BigDecimal number) throws DAOException;
    void update(Stock stock) throws DAOException;
}