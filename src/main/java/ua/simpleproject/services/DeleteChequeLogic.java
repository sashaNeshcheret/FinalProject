package ua.simpleproject.services;

import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.DAOSoldProducts;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.SoldProducts;
import ua.simpleproject.exception.DAOException;
import java.util.Objects;

public class DeleteChequeLogic {
    private DAOSoldProducts daoSoldProducts = DAOFactory.getDaoFactory().getDaoSoldProducts();
    //private Dao

    public void delete(int id) throws DAOException {




        SoldProducts soldProducts = null; //daoSoldProducts.

    }
}
