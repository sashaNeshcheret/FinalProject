package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.services.OpenChequeLogic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class OpenCheckCommand implements ActionCommand {
    private OpenChequeLogic openChequeLogic = new OpenChequeLogic();
    private static final Logger logger = Logger.getLogger(OpenCheckCommand.class);

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
        if(openChequeLogic.openCheque(login)){
            page = "/jsp/openCheque.jsp";
            return page;
        } else {
            request.setAttribute("openCheque", "Sorry, but for technical reasons you can not open a check");
            page = "/jsp/error/error1.jsp";
        }
        return page;
    }
}
