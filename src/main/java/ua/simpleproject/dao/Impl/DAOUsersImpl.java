package ua.simpleproject.dao.Impl;


import ua.simpleproject.dao.DAOUsers;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsersImpl implements DAOUsers {
    private static  final String SQL_SELECT = "SELECT * FROM users where login = ?";
    private static  final String SQL_INSERT = "INSERT INTO cash_register.users (position, name, login, password, " +
            "check_word) VALUES(?,?,?,?,?)";
    private static  final String SQL_DELETE = "DELETE from cash_register.users WHERE login = ?";

    protected DAOUsersImpl(){
    }

    /**Insert object user in database "User"
     *
     * @param user
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(User user) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setString(1, user.getPosition());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCheckWord());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException();
        }
    }

    /**Select user in DB by login
     *
     * @param login
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public User read(String login) throws DAOException{
        User user = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setPosition(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setLogin(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setCheckWord(resultSet.getString(6));
                user.setSalary(resultSet.getBigDecimal(7));
                user.setNumberMistake(resultSet.getInt(8));
                user.setExperience(resultSet.getInt(9));
                }
        } catch (SQLException | ConnectionException e){
            throw new DAOException(String.format("Method read(login: '%s') has thrown an exception.", login), e);
        }
        return user;
    }

    /**Delete user in DB by login
     *
     * @param login
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(String login) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method delete(login: '%s') has thrown an exception.", login), e);
        }
    }
}
