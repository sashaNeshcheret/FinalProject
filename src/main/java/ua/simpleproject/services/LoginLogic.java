package ua.simpleproject.services;

import org.apache.log4j.Logger;
import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.validation.EnterDataValidator;

public class LoginLogic {
    private static final Logger logger = Logger.getLogger(LoginLogic.class);
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOUsers daoUsers = daoFactory.getDaoUsers();

    /**Check login and password in accordance of BD
     *
     * @param enterLogin
     * @param enterPass
     * @return
     */
   public boolean checkLogin(String enterLogin, String enterPass) {
       User user = null;
       logger.info(String.format("check user with login '%s'", enterLogin));
       try {
           if(enterLogin == null){
               return false;
           }
           user = daoUsers.read(enterLogin);
           if(enterPass == null || user == null){
               return false;
           }
           if(enterLogin.equals(user.getLogin()) && enterPass.equals(user.getPassword())){
               return true;
           }
       } catch (DAOException e) {
           logger.info(String.format("check user with login '%s'", enterLogin));
       }
       return false;
   }

    /**Get object of user by its login
     *
     * @param login
     * @return
     */
   public User getUserByLogin(String login){
       User user = null;
       try {
           logger.info("session time out or you don't login");
           user = DAOFactory.getDaoFactory().getDaoUsers().read(login);
           return user;
       } catch (DAOException e) {
           e.printStackTrace();// log4j
       }
       return user;
   }

   public String validateLoginPassword(String login, String pass){
       String message = null;
       if(!EnterDataValidator.isValidLogin(login)){
           message = "Login is not correct";
       }
       if(!EnterDataValidator.isValidLogin(pass)){
           message = "Password is not correct";
       }
       return message;
   }
}