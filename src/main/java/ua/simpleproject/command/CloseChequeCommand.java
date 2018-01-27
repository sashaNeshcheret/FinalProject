package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ChequeReports;
import ua.simpleproject.dto.ProductCurrentCheck;
import ua.simpleproject.services.AddProductLogic;
import ua.simpleproject.services.CloseChequeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
            ChequeReports chequeReports = closeChequeLogic.closeCheque(login);

            List<ProductCurrentCheck> productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            page = "/jsp/closeCheque.jsp";

            request.setAttribute("chequeReports", chequeReports);
            request.setAttribute("list", productCurrentCheckList);
            return page;
        }catch(Exception e){
            page = "/jsp//error/error1.jsp";
            return page;
        }
    }
}