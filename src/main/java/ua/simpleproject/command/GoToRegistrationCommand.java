package ua.simpleproject.command;

import javax.servlet.http.HttpServletRequest;

public class GoToRegistrationCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        String page = "/WEB-INF/jsp/registration.jsp";
        return page;
    }
}
