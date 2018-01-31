package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.services.CreateProductLogic;
import ua.simpleproject.services.DeleteChequeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteChequeCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(CreateProductCommand.class);
    private DeleteChequeLogic deleteChequeLogic = new DeleteChequeLogic();

    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheque> productCurrentCheckList = new ArrayList<>();
        String page = null;
        String idStr = (String) request.getParameter("id");
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/login.jsp";
            logger.error("");
            return page;
        }
        try{
            //deleteChequeLogic.createProductStock(product);
            page = "/WEB-INF/jsp/general.jsp";
            return page;
        }catch(Exception e){
            page = "/WEB-INF/jsp//error/error1.jsp";
            logger.error("", e);
            return page;
        }
    }
}
