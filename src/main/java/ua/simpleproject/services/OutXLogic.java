package ua.simpleproject.services;

import ua.simpleproject.dao.DAOChequeReports;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutXLogic {
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOChequeReports daoChequeReports = daoFactory.getDaoChequeReports();
    private DAOUsers daoUsers = daoFactory.getDaoUsers();

    public List<ChequeReports> outXReport(String login) throws DAOException {
        List<ChequeReports> list = new ArrayList<>();
        List<ChequeReports> list1 = new ArrayList<>();
        //int date = GregorianCalendar.DATE;
        LocalDate now = LocalDate.now(); // current date and time
        //LocalDateTime midnight = now.toLocalDate().atStartOfDay();
        User user = daoUsers.read(login);

        list1 = outXReport(now, user.getId());
        return list1;
    }

    public List<ChequeReports> outXReport(LocalDate midnight, int userId){
        List<ChequeReports> chequeReportsList = null;
        try {
            chequeReportsList = daoChequeReports.read(midnight, userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return chequeReportsList;
    }
}
