package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.AddProductStockLogic;
import ua.simpleproject.services.LoginLogic;
import ua.simpleproject.services.OpenChequeLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private LoginLogic loginLogic = new LoginLogic();
    private OpenChequeLogic openChequeLogic = new OpenChequeLogic();
    private AddProductStockLogic addProductStockLogic = new AddProductStockLogic();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_POSITION = "position";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        // selection from request login and password
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        Locale locale = request.getLocale();
        List<ProductCurrentCheque> list = null;
        //validate login and password
        String message = loginLogic.validateLoginPassword(login, pass);
        if(Objects.nonNull(message)){
            request.setAttribute("errorLoginPassMessage", message);
            page = "/login.jsp";
            return  page;
        }

        //check login and password
        if (loginLogic.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            String userLogin = (String) session.getAttribute(PARAM_NAME_LOGIN);
            if(Objects.isNull(userLogin)){
                session.setAttribute(PARAM_NAME_LOGIN, login);
                session.setMaxInactiveInterval(600);
            }else{
                session.setAttribute(PARAM_NAME_LOGIN, userLogin);
            }
            User user = loginLogic.getUserByLogin(login);
            String position = null;
            if(user != null){
                position = user.getPosition();
                session.setAttribute(PARAM_NAME_POSITION, position);
            }
            if("merchant".equals(position)){
                int numberPage = 1;
                try {
                    list = addProductStockLogic.getStock(numberPage);
                } catch (DAOException e) {
                    page = "/WEB-INF/jsp/error/error1.jsp";
                    return page;
                }
                request.setAttribute("list", list);
                request.setAttribute("numberPage", numberPage);
                page = "/WEB-INF/jsp/generalMerchant.jsp";
                return page;
            }else if("cashier".equals(position)){
                openChequeLogic.openCheque(user.getLogin());
                page = "/WEB-INF/jsp/openCheque.jsp";
                return page;
            }
            else if("senior_cashier".equals(position)){
                page = "/WEB-INF/jsp/general.jsp";
                return page;
            }
            page = "/WEB-INF/jsp/error/error1.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Insert data doesn't possess to any user");
            page = "/login.jsp";
            return page;
        }
        return page;
    }
}
