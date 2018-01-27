package ua.simpleproject.authentification;

import org.apache.log4j.Logger;
import ua.simpleproject.command.AddProductCommand;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.LoginLogic;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Objects;

public class Authentification {
    //public abstract class Authentication {
    private Logger logger = Logger.getLogger(Authentification.class);
    private LoginLogic loginLogic = new LoginLogic();

    public Authentification() {
    }


    /**
         * Return user status (is log in or not)
         * @param session - Current session
         * @return boolean status - log in or not
         */
        public boolean isUserLogIn(HttpSession session){
            String login = (String)session.getAttribute("login");
            String password = "";
            boolean isIn = false;

            try(ConnectionWrapper connection = TransactionManager.getConnection()) {
                    password = DAOFactory.getDaoFactory().getDaoUsers().read(login).getPassword();
                    isIn = (Objects.nonNull(login) || Objects.equals(login, "")) && loginLogic.checkLogin(login, password);
            } catch (ConnectionException e) {
                logger.error("Transaction (Authentication.isUserLogIn()) exception : Connection with DB error!");
                isIn = false;
            } catch (SQLException | DAOException e) {
                logger.error("Transaction (Authentication.isUserLogIn()) exception : work with DB error!");
                isIn = false;
            }

            return isIn;
        }

        /**
         * Return user role (is admin or not)
         * @param session - Current session
         * @return boolean status - admin or not
         */
        public static boolean isAdmin(HttpSession session) {
            String login = (String)session.getAttribute("login");
            if(Objects.equals(login, "Admin") || Objects.equals(login, "Admin2"))
                return true;
            return false;
        }

}
