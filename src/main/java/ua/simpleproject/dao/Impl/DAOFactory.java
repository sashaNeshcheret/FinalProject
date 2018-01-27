package ua.simpleproject.dao.Impl;

import ua.simpleproject.dao.*;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();
    private DAOUsers daoUsers = new DAOUsersImpl();
    private DAOStock daoStock = new DAOStockImpl();
    private DAOProduct daoProduct = new DAOProductImpl();
    private DAOChequeReports daoChequeReports = new DAOChequeReportsImpl();
    private DAOCurrentCheque daoCurrentCheque = new DAOCurrentChequeImpl();

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory;
    }
    public DAOUsers getDaoUsers() {
        return daoUsers;
    }
    public DAOStock getDaoStock() {
        return daoStock;
    }
    public DAOProduct getDaoProduct() {
        return daoProduct;
    }
    public DAOChequeReports getDaoChequeReports() {
        return daoChequeReports;
    }
    public DAOCurrentCheque getDaoCurrentCheque() {
        return daoCurrentCheque;
    }
}
