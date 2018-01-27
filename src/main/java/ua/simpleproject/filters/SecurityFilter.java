package ua.simpleproject.filters;

import org.apache.log4j.Logger;
import ua.simpleproject.authentification.Authentification;
import ua.simpleproject.command.AddProductCommand;
import ua.simpleproject.configaration.SecurityConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SecurityFilter implements Filter {
    private static final SecurityConfiguration config = SecurityConfiguration.getInstance();
    private Authentification authentification = new Authentification();
    private Logger logger = Logger.getLogger(AddProductCommand.class);
    //roles
    public static final String ALL = "all";
    public static final String AUTH = "authorised";

    private Map<String,String> grant = new HashMap<>();

    public static SecurityConfiguration getInstance(){
        return config;
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SecurityConfiguration configuraton = SecurityConfiguration.getInstance();
        String command = getStringCommand(request.getRequestURI(),configuraton.getEndPoints());
        String role = configuraton.security(command);


        //All
        if (Objects.equals(role, SecurityConfiguration.ALL)) {
            logger.debug("Filter.Security (SecurityFilter.doFilter()) info : Successful request for ALL.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //Users
        if (Objects.equals(role, SecurityConfiguration.AUTH)
                && authentification.isUserLogIn(request.getSession())) {
            logger.debug("Filter.Security (SecurityFilter.doFilter()) info : Successful request for AUTH.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (Objects.equals(role, SecurityConfiguration.AUTH)
                && !authentification.isUserLogIn(request.getSession())) {
            logger.info("Filter.Security (SecurityFilter.doFilter()) info : Not authorised request for AUTH.");
            request.setAttribute("error", "Authentication failed");
            request.getRequestDispatcher("error1.jsp").forward(request, response);
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    public void destroy() {
    }

    private String getStringCommand(String URI, Set<String> endPoints){
        for (String endPoint: endPoints) {
            if(URI.contains(endPoint))
                return endPoint;
        }
        return null;
    }
}
