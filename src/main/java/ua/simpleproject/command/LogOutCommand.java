package ua.simpleproject.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements ActionCommand {
    private Logger logger = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/login.jsp";
        //deleting session
        request.getSession().invalidate();
        return page;
    }
}