package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.dto.ProductCurrentCheck;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.AddProductStockLogic;
import ua.simpleproject.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class GetProductStockCommand implements ActionCommand{
    private LoginLogic loginLogic = new LoginLogic();
    private AddProductStockLogic addProductStockLogic = new AddProductStockLogic();
    private static final String PARAM_NAME_NUMBER_PAGE = "numberPage";
    private Logger logger = Logger.getLogger(GetProductStockCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheck> list = null;
        String page = null;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/jsp//login.jsp";
            logger.error("session time out or you don't login");
            return page;
        }
        try{
            String pageStock = null;
            System.out.println(request.getParameter(PARAM_NAME_NUMBER_PAGE));
            if (request.getParameter(PARAM_NAME_NUMBER_PAGE) == null) {
                pageStock = "0";
            } else {
                pageStock = request.getParameter(PARAM_NAME_NUMBER_PAGE);
            }
            int numberPage = Integer.parseInt(pageStock);
            list = addProductStockLogic.getStock(++numberPage);
            request.setAttribute("list", list);
            request.setAttribute("numberPage", numberPage);
            page = "/jsp/generalMerchant.jsp";
            return page;
        }catch(DAOException e){
            page = "/jsp//error/error1.jsp";
            logger.error("Error connection with data base", e);
            return page;
        }
    }
}
