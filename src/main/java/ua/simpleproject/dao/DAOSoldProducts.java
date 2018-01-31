package ua.simpleproject.dao;

import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.entity.SoldProducts;
import ua.simpleproject.exception.DAOException;
import java.util.List;

public interface DAOSoldProducts {
    void create(SoldProducts soldProducts) throws DAOException;
    //void update(CurrentCheque currentCheque) throws DAOException;
    //List<CurrentCheque> read(String login) throws DAOException;
    //void delete(int userId) throws DAOException;
}
