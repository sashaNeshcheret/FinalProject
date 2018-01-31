package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.CloseChequeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CloseChequeCommand implements ActionCommand {
    private CloseChequeLogic closeChequeLogic = new CloseChequeLogic();
    private AddProductLogic addProductLogic = new AddProductLogic();
    private Logger logger = Logger.getLogger(CloseChequeCommand.class);



    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        try{
            BigDecimal amount = closeChequeLogic.closeCheque(login);
            if(Objects.equals(amount, new BigDecimal("0"))){
                page = "/WEB-INF/jsp/general.jsp";
                return page;
            }
            List<ProductCurrentCheque> productCurrentCheckList = addProductLogic.getCurrentCheque(login);

            request.setAttribute("amount", amount);
            request.setAttribute("list", productCurrentCheckList);
            page = "/WEB-INF/jsp/closeCheque.jsp";
            return page;
        }catch(DAOException e){
            page = "/WEB-INF/jsp//error/error1.jsp";
            return page;
        }
    }
}