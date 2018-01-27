package ua.simpleproject.dao;


import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.exception.DAOException;

import java.util.List;

public interface DAOCurrentCheque {

    void create(CurrentCheque currentCheque) throws DAOException;
    void update(CurrentCheque currentCheque) throws DAOException;
    List<CurrentCheque> read(String login) throws DAOException;
    void delete(int userId) throws DAOException;
}
