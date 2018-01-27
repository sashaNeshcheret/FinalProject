package ua.simpleproject.dao;

import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;

public interface DAOUsers {
    void create(User user) throws DAOException;
    User read(String login) throws DAOException;
    void delete(String login) throws DAOException;
}