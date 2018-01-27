package ua.simpleproject.services;

import ua.simpleproject.dao.DAOChequeReports;
import ua.simpleproject.dao.DAOCurrentCheque;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;

public class CloseChequeLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCurrentCheque daoCurrentCheque = daoFactory.getDaoCurrentCheque();
    private DAOChequeReports daoChequeReports = daoFactory.getDaoChequeReports();

    public ChequeReports closeCheque(String login) throws DAOException {
        BigDecimal priceCheck = new BigDecimal(0);
        List<CurrentCheque> list = daoCurrentCheque.read(login);
        int userId = 0;
        for (CurrentCheque currentCheque : list){
            priceCheck = currentCheque.getResultPrice().add(priceCheck);
            userId = currentCheque.getUserId();
        }
        ChequeReports chequeReports = new ChequeReports(userId, list.size(), priceCheck);
        daoChequeReports.create(chequeReports);
        return chequeReports;
    }
}
