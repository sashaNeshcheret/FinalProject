package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.TransactionException;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.PayAndPrintChequeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PayAndPrintChequeCommand implements ActionCommand{
    private static final Logger logger = Logger.getLogger(AddProductCommand.class);
    private AddProductLogic addProductLogic = new AddProductLogic();
    private PayAndPrintChequeLogic payPrint = new PayAndPrintChequeLogic();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME = "name";



    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheque> productCurrentCheckList = new ArrayList<>();
        ChequeReports chequeReports = null;
        String page = null;
        String name = (String) request.getParameter(PARAM_NAME);

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);
        if (Objects.isNull(login)){
            request.setAttribute("errorLoginPassMessage", "session time out or you don't login");
            page = "/login.jsp";
            logger.error("session time out or you don't login");
            return page;
        }
        try{
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            chequeReports = payPrint.payAndPrintCheque(login);
            System.out.println("sqldate -" + chequeReports.getDate());
            Date date = chequeReports.getDate();
            System.out.println("date -" + date);
            request.setAttribute("chequeReports", chequeReports);
            request.setAttribute("date", date);
            request.setAttribute("list", productCurrentCheckList);
            page = "/WEB-INF/jsp/payPrintCheque1.jsp";
            return page;


        }catch(DAOException e){
            page = "/WEB-INF/jsp//openedCheque.jsp";
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            request.setAttribute("list", productCurrentCheckList);
            request.setAttribute("wrongEnter",
                    "Insert number isn't a code or quantity of it");
            logger.error("Insert number isn't a code or quantity of it", e);
            return page;
        }catch(TransactionException e){
            page = "/WEB-INF/jsp//openedCheque.jsp";
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            request.setAttribute("list", productCurrentCheckList);
            request.setAttribute("wrongEnter",
                    "An error occurred during the transaction");
            logger.error("quantity of goods in stock less than needed", e);
            return page;
        }
    }
}
