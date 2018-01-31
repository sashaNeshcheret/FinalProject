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

public class PayAndPrintChequeLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCurrentCheque daoCurrentCheque = daoFactory.getDaoCurrentCheque();
    private DAOChequeReports daoChequeReports = daoFactory.getDaoChequeReports();
    private DAOSoldProducts daoSoldProducts = daoFactory.getDaoSoldProducts();



    public ChequeReports payAndPrintCheque(String login) throws DAOException, TransactionException {
        BigDecimal priceCheck = new BigDecimal(0);
        ChequeReports chequeReports = null;
        try {
            TransactionManager.beginTransaction();
            List<CurrentCheque> list = daoCurrentCheque.read(login);
            if (list.size() == 0) {
                return null;
            }
            int userId = 0;
            for (CurrentCheque currentCheque : list) {
                priceCheck = currentCheque.getResultPrice().add(priceCheck);
                userId = currentCheque.getUserId();
            }
            chequeReports = new ChequeReports(userId, list.size(), priceCheck);
            daoChequeReports.create(chequeReports);
            chequeReports = daoChequeReports.readLast();
            for(CurrentCheque currentCheque : list){
                SoldProducts soldProducts = new SoldProducts(chequeReports.getId(), currentCheque.getProductID(), currentCheque.getCount());
                daoSoldProducts.create(soldProducts);
            }
            TransactionManager.endTransaction();
        } catch (TransactionException | ConnectionException e) {
            try {
                TransactionManager.rollBack();
            } catch (SQLException e1) {
                throw new TransactionException("Exception in Transaction Pay and Print Cheque Logic",e);
            }
        }
        return chequeReports;
    }
}
