package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.services.OutXLogic;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OutXCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(OutXCommand.class);
    private OutXLogic outXReport = new OutXLogic();

    @Override
    public String execute(HttpServletRequest request) {
        List<ChequeReports> chequeReportsList = new ArrayList<>();
        String page = null;

        Locale locale = request.getLocale();
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");

        if (Objects.isNull(login)){
            page = "/login.jsp";
            logger.error("");
            return page;
        }
        try{
            chequeReportsList = outXReport.outXReport();
            page = "/WEB-INF/jsp/XReport.jsp";
            request.setAttribute("chequeReportsList", chequeReportsList);
            return page;
        }catch(Exception e){
            page = "/WEB-INF/jsp//error/error1.jsp";
            logger.error("", e);
            return page;
        }
    }
}
