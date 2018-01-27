package ua.simpleproject.servlets;

import ua.simpleproject.command.ActionCommand;
import ua.simpleproject.command.ActionFactory;
import ua.simpleproject.configaration.MessageManager;
import ua.simpleproject.transactions.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


public class Servlet  extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        if (ConnectionPool.getConnectionPool().getDataSource() == null) {
            try {
                Context context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/cash_register");//
                ConnectionPool.getConnectionPool().setDataSource(dataSource);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        // determination of command which comes from JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        /*
         * call of realised method execute() and transmition of parameters
         * of class of concret object
         */
        page = command.execute(request);
        if (page != null) {
            request.setAttribute("names", " sorry, but here name is not avialable");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // Call of page of answer to request
            dispatcher.forward(request, response);
        } else {
//page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}
