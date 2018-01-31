package ua.simpleproject.services;

import ua.simpleproject.dao.DAOChequeReports;
import ua.simpleproject.dao.DAOZReportTime;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.User;
import ua.simpleproject.entity.ZReportTime;
import ua.simpleproject.exception.DAOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutXLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOChequeReports daoChequeReports = daoFactory.getDaoChequeReports();
    private DAOZReportTime daozReportTime = daoFactory.getDaoZReportTime();

    public List<ChequeReports> outXReport() throws DAOException {
        List<ChequeReports> chequeReportsList = null;
        ZReportTime zReportTime = daozReportTime.read();
        chequeReportsList = daoChequeReports.read(zReportTime.getZTime());
        return chequeReportsList;
    }
}
