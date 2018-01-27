package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.dto.ProductCurrentCheck;
import ua.simpleproject.services.AddProductLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

public class OpenCurrentCheckCommand implements ActionCommand {
    private AddProductLogic addProductLogic = new AddProductLogic();
    private static final Logger logger = Logger.getLogger(OpenCurrentCheckCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        if(Objects.isNull(login)){
            request.setAttribute("errorLogin", "Sorry, but you do not have the right to open a check");
            page = "/jsp//login.jsp";
            return page;
        }

        List<ProductCurrentCheck> list = null;
        list = addProductLogic.getCurrentCheque(login);
        request.setAttribute("list", list);
        page = "/jsp/openedCheque.jsp";
        return page;
    }
}
