package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.services.OpenChequeLogic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class GoToMainCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/WEB-INF/jsp/general.jsp";
        return page;
    }
}
