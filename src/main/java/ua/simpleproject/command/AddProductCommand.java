package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.services.AddProductLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AddProductCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private AddProductLogic addProductLogic = new AddProductLogic();


    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheque> productCurrentCheckList = new ArrayList<>();
        String page = null;
        String name = (String) request.getParameter("name");
        String codeStr = (String) request.getParameter("code");
        String numberStr = (String) request.getParameter("number");

        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(PARAM_NAME_LOGIN);
        if (Objects.isNull(login)){
            request.setAttribute("errorLoginPassMessage", "session time out or you don't login");
            page = "/login.jsp";
            logger.error("session time out or you don't login");
            return page;
        }
        if ((name.isEmpty() && codeStr.isEmpty()) || numberStr.isEmpty()){
            page = "/WEB-INF/jsp//openedCheque.jsp";
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            request.setAttribute("list", productCurrentCheckList);
            request.setAttribute("wrongEnter", "fill in the required fields");
            logger.error("");
            return page;
        }
        try{
            productCurrentCheckList = addProductLogic.addProduct(name, codeStr, numberStr, login);
            page = "/WEB-INF/jsp//openedCheque.jsp";
            request.setAttribute("list", productCurrentCheckList);
            return page;
        }catch(DAOException e){
            page = "/WEB-INF/jsp//openedCheque.jsp";
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            request.setAttribute("list", productCurrentCheckList);
            request.setAttribute("wrongEnter",
                    "Insert number isn't a code or quantity of it");
            logger.error("Insert number isn't a code or quantity of it", e);
            return page;
        }catch(LogicException e){
            page = "/WEB-INF/jsp//openedCheque.jsp";
            productCurrentCheckList = addProductLogic.getCurrentCheque(login);
            request.setAttribute("list", productCurrentCheckList);
            request.setAttribute("numberProduct", "quantity of goods in stock less than needed");
            logger.error("quantity of goods in stock less than needed", e);
            return page;
        }
    }
}