package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.dto.ProductCurrentCheck;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.resourceProperties.LanguagePropsManager;
import ua.simpleproject.services.AddProductStockLogic;
import ua.simpleproject.services.LoginLogic;
import ua.simpleproject.validation.EnterDataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private LoginLogic loginLogic = new LoginLogic();
    private AddProductStockLogic addProductStockLogic = new AddProductStockLogic();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        // selection from request login and password
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        Locale locale = request.getLocale();
        List<ProductCurrentCheck> list = null;
        //check login and password
        if(EnterDataValidator.isValidLogin(login)){
            request.setAttribute("errorLoginPassMessage", "Login is not correct");
            page = "/jsp/login.jsp";
            return page;
        }
        if(EnterDataValidator.isValidPassword(pass)){
            request.setAttribute("errorLoginPassMessage", "Password is not correct");
            page = "/jsp/login.jsp";
            return page;
        }
        if (loginLogic.checkLogin(login, pass)) {
            HttpSession session = request.getSession(true);
            String userLogin = (String) session.getAttribute(PARAM_NAME_LOGIN);
            if(Objects.isNull(userLogin)){
                session.setAttribute(PARAM_NAME_LOGIN, login);
                session.setMaxInactiveInterval(100);
            }else{
                session.setAttribute(PARAM_NAME_LOGIN, userLogin);
            }
            User user = loginLogic.getUserByLogin(login);
            String position = null;
            if(user != null){
                position = user.getPosition();
            }
            if("merchant".equals(position)){
                int numberPage = 1;
                try {
                    list = addProductStockLogic.getStock(numberPage);
                } catch (DAOException e) {
                    page = "/jsp/error/error1.jsp";
                    return page;
                }
                request.setAttribute("list", list);
                request.setAttribute("numberPage", numberPage);
                page = "/jsp/generalMerchant.jsp";
                return page;
            }else if("senior_cashier".equals(position) || "cashier".equals(position)){
                page = "/jsp/general.jsp";
                return page;
            }
            page = "/jsp/error/error1.jsp";
        } else {
            request.setAttribute("errorLoginPassMessage", "Insert data doesn't possess to any user");
            page = "/jsp/login.jsp";
            return page;
        }
        return page;
    }
}
