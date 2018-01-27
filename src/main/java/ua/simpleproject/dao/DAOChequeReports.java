package ua.simpleproject.dao;

import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.exception.DAOException;

import java.time.LocalDate;
import java.util.*;

public interface DAOChequeReports {
    void create(ChequeReports chequeReports) throws DAOException;
    List<ChequeReports> read(LocalDate currentTime , int userID) throws DAOException ;
}
