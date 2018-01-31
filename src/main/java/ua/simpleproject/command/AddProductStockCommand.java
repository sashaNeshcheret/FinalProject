package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.AddProductStockLogic;
import ua.simpleproject.services.LoginLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class AddProductStockCommand implements ActionCommand{
    private LoginLogic loginLogic = new LoginLogic();
    private AddProductStockLogic addProductStockLogic = new AddProductStockLogic();
    private static final String PARAM_NAME_NUMBER_PAGE = "numberPage";
    private Logger logger = Logger.getLogger(AddProductStockCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        List<ProductCurrentCheque> list = null;
        Product product = new Product();
        String page = null;
        String pageStock = null;
        String name = (String) request.getParameter("name");
        String codeStr = (String) request.getParameter("code");
        String numberStr = (String) request.getParameter("number");
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if (Objects.isNull(login)){
            page = "/login.jsp";
            logger.error("session time out or you don't login");
            return page;
        }
        int code = Integer.parseInt(codeStr);
        BigDecimal number = new BigDecimal(numberStr);
        product.setName(name);
        product.setCodeProduct(code);
        try{
            addProductStockLogic.addProductStock(product, number);
            if (request.getParameter(PARAM_NAME_NUMBER_PAGE) == null) {
                pageStock = "0";
            } else {
                pageStock = request.getParameter(PARAM_NAME_NUMBER_PAGE);
            }
            try {
                int numberPage = Integer.parseInt(pageStock);
                list = addProductStockLogic.getStock(++numberPage);
                request.setAttribute("list", list);
                request.setAttribute(PARAM_NAME_NUMBER_PAGE, numberPage);
                page = "/WEB-INF/jsp/generalMerchant.jsp";
                return page;
            } catch (DAOException e) {
                page = "/WEB-INF/jsp/error/error1.jsp";
                return page;
            }
        }catch(DAOException e){
            page = "/WEB-INF/jsp//error/error1.jsp";
            logger.error("Mistake work with DB", e);
            return page;
        }
    }
}
