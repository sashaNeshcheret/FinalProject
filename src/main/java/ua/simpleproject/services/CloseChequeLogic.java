package ua.simpleproject.services;

import ua.simpleproject.dao.DAOChequeReports;
import ua.simpleproject.dao.DAOCurrentCheque;
import ua.simpleproject.dao.DAOSoldProducts;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.entity.SoldProducts;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.TransactionException;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CloseChequeLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCurrentCheque daoCurrentCheque = daoFactory.getDaoCurrentCheque();
    private DAOChequeReports daoChequeReports = daoFactory.getDaoChequeReports();
    private DAOSoldProducts daoSoldProducts = daoFactory.getDaoSoldProducts();


    public BigDecimal closeCheque(String login) throws DAOException {
        BigDecimal priceCheck = new BigDecimal("0");
        List<CurrentCheque> list = daoCurrentCheque.read(login);
        if(list.size() == 0){
            return null;
        }
        for (CurrentCheque currentCheque : list){
            priceCheck = currentCheque.getResultPrice().add(priceCheck);
        }
        return priceCheck;
    }
}
