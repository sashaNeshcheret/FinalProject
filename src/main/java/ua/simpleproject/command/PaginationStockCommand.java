package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PaginationStockCommand implements ActionCommand {
    private static final String PARAM_NAME_NUMBER_PAGE = "numberPage";
    private static final String PARAM_NAME_LOGIN = "login";
    private LoginLogic loginLogic = new LoginLogic();
    private Logger logger = Logger.getLogger(PaginationStockCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheque> list = null;
        String page = null;
        /*HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/jsp//login.jsp";
            logger.error("");
            return page;
        }
        User user = loginLogic.getUserByLogin(login);
        String position = null;
        if (user != null) {
            position = user.getPosition();
        }
            if ("merchant".equals(position)) {
                String pageStock = null;
                if (request.getParameter(PARAM_NAME_NUMBER_PAGE) == null) {
                    pageStock = "0";
                } else {
                    pageStock = request.getParameter(PARAM_NAME_NUMBER_PAGE);
                }
                try {
                    int numberPage = Integer.parseInt(pageStock);
                    list = loginLogic.getStock(++numberPage);
                    request.setAttribute("list", list);
                    request.setAttribute(PARAM_NAME_NUMBER_PAGE, numberPage);
                    page = "/jsp/generalMerchant.jsp";
                    return page;
                } catch (DAOException e) {
                    page = "/jsp/error/error1.jsp";
                    return page;
                }
            } else if (position.equals("senior_cashier") || position.equals("cashier")) {
                page = "/jsp/general.jsp";
                return page;
            }
        page = "/jsp/login.jsp";*/
        return  page;
    }
}
