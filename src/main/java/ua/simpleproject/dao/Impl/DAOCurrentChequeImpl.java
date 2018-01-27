package ua.simpleproject.dao.Impl;


import ua.simpleproject.dao.DAOCurrentCheque;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCurrentChequeImpl implements DAOCurrentCheque {
    private static final String SQL_SELECT_BY_PRODUCT_ID = "SELECT * FROM current_check WHERE product_id = ?";
    private static final String SQL_SELECT_BY_USER_LOGIN = "SELECT * FROM current_check WHERE user_id = " +
            "(SELECT userId FROM users WHERE login = ?)";
    private static final String SQL_SELECT = "SELECT * FROM current_check WHERE " +
            "((product_id = (SELECT id FROM products WHERE code_product = ?)) & (user_id = ?))";
    private static final String SQL_INSERT = "INSERT INTO current_check (product_id, user_id, count, result_price) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE_BY_PRODUCT_ID = "UPDATE current_check SET count = ?, result_price = ?  WHERE product_id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE current_check SET count = ?, result_price = ?  WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM current_check WHERE user_id = ?";



    protected DAOCurrentChequeImpl(){
    }

    /**insert object currentCheque into database
     *
     * @param currentCheque describe current objects
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(CurrentCheque currentCheque) throws DAOException{
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement pstatement = connection.preparedStatement(SQL_INSERT);
            pstatement.setInt(1, currentCheque.getProductID());
            pstatement.setInt(2, currentCheque.getUserId());
            pstatement.setBigDecimal(3, currentCheque.getCount());
            pstatement.setBigDecimal(4, currentCheque.getResultPrice());
            pstatement.executeUpdate();
        }catch (SQLException | ConnectionException sqlEx){
            System.out.println("Mistake in insert");
        }
    }

    /** Refresh data in BD of current cheque
     *
     * @param currentCheque
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void update(CurrentCheque currentCheque) throws DAOException{
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement pstatement = connection.preparedStatement(SQL_UPDATE_BY_PRODUCT_ID); // "UPDATE cash_register.current_check SET count = ?, result_price = ?  WHERE product_id = ?";
            pstatement.setBigDecimal(1, currentCheque.getCount());
            pstatement.setBigDecimal(2, currentCheque.getResultPrice());/////////////////////////////
            pstatement.setInt(3, currentCheque.getProductID() );
            pstatement.executeUpdate();
        }catch (SQLException | ConnectionException sqlEx){
            System.out.println("Mistake in update");
            sqlEx.printStackTrace();
            return;
        }
    }

    /** Select objects in DB by user login
     *
     * @param login
     * @return return list of objects of current cheque
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<CurrentCheque> read(String login)  throws DAOException{
        List<CurrentCheque> list = new ArrayList<>();
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_BY_USER_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CurrentCheque currentCheque = new CurrentCheque();
                currentCheque.setId(resultSet.getInt(1));
                currentCheque.setProductID(resultSet.getInt(2));
                currentCheque.setUserId(resultSet.getInt(3));
                currentCheque.setCount(resultSet.getBigDecimal(4));
                currentCheque.setResultPrice(resultSet.getBigDecimal(5));
                list.add(currentCheque);
            }
        } catch (SQLException | ConnectionException sqlEx) {
            System.out.println("Mistake in getting opened list");
            sqlEx.printStackTrace();
        }
        return list;
    }

    /**Delete objects of current cheque by user's ID
     *
     * @param userId
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void delete(int userId) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_DELETE);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            System.out.println("open check");
        }catch (SQLException  | ConnectionException sqlEx){
            System.out.println("Mistake in open check");
            throw new DAOException();
        }
    }
    /*
    public void add(CurrentCheque currentCheck){
        int id = -1;
        BigDecimal count = new BigDecimal(0);
        try(Connection connection = ConnectionPool.getConnection()){
            ResultSet resultSet = selectFromCurrentCheck(connection, currentCheck);
            while (resultSet.next()){
                id = resultSet.getInt(1);
                count = resultSet.getBigDecimal(4);
            }
            if(id == -1){
                insertToCurrentCheck(currentCheck);
            }else{
                updateCurrentCheck(currentCheck);
            }
        }catch (SQLException sqlEx){
            System.out.println ("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void addToCurrentCheckByCode(Connection connection, int code, int userId, int number){
        int id = -1;
        //int userId = 0;
        int count = 0;
        try{
            ResultSet resultSet = selectFromCurrentCheck(connection, code, userId);
            while (resultSet.next()){
                id = resultSet.getInt(1);
                //userId = resultSet.getInt(2);
                count = resultSet.getInt(4);
            }
            if(id == -1){
                insertToCurrentCheck(connection, userId, code, number);
            }else{
                updateCurrentCheck(id, code, count, number);
            }
        }catch (SQLException sqlEx){
            System.out.println("Mistake in adding");
            sqlEx.printStackTrace();
            return;
        }
    }
    public ResultSet selectFromCurrentCheck(Connection connection, CurrentCheque currentCheck) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_PRODUCT_ID);
        preparedStatement.setInt(1,currentCheck.getProductID());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public ResultSet selectFromCurrentCheck(Connection connection, int code, int userId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
        preparedStatement.setInt(1, code);
        preparedStatement.setInt(2, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public void insertToCurrentCheck(Connection connection, int userID, int code, int number){
        try{

            //create method select from products in DAOCurrentCheque. Why not?
            int productId = 0;
            int priceForOne = 0;
            String sqlSelect = "SELECT * FROM cash_register.products where code_product = ?";
            PreparedStatement selectPSstatement = connection.prepareStatement(sqlSelect);
            selectPSstatement.setInt(1, code);
            ResultSet resultSet = selectPSstatement.executeQuery();
            while (resultSet.next()){
                productId = resultSet.getInt(1);
                priceForOne = resultSet.getInt(5);
            }
            PreparedStatement pstatement = connection.prepareStatement(SQL_INSERT);
            pstatement.setInt(1, productId);
            pstatement.setInt(2, userID);
            pstatement.setInt(3, number);
            pstatement.setInt(4, number*priceForOne);
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in insert to Current Check");
            sqlEx.printStackTrace();
            return;
        }
    }
    public void update(int id, int code, int count, int number){
        int productId = 0;
        int priceForOne = 0;
        try(Connection connection = ConnectionPool.getConnection()){
            String sqlSelect = "SELECT * FROM cash_register.products where code_products = ?";
            PreparedStatement selectPSstatement = connection.prepareStatement(sqlSelect);
            selectPSstatement.setInt(1, code);
            ResultSet resultSet = selectPSstatement.executeQuery();
            while (resultSet.next()){
                productId = resultSet.getInt(1);
                priceForOne = resultSet.getInt(5);
            }
            PreparedStatement pstatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            pstatement.setInt(1, number+count);
            pstatement.setInt(2, ((number)+count)*priceForOne);
            pstatement.setInt(3, id );
            pstatement.executeUpdate();
        }catch (SQLException sqlEx){
            System.out.println("Mistake in update current Check");
            sqlEx.printStackTrace();
            return;
        }
    }
    public List<CurrentCheque> getOpenedCheck() {
        List<CurrentCheque> list = new ArrayList<>();
        try(Connection connection = ConnectionPool.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cash_register.current_check WHERE current_check.id > 0");
            int id=0, productId=0, userId=0;
            BigDecimal count;
            BigDecimal resultPrice;
            while (resultSet.next()){
                id = resultSet.getInt(1);
                System.out.print("id = " + id);
                productId = resultSet.getInt(2);
                System.out.print(", product_id = " + productId);
                userId = resultSet.getInt(3);
                System.out.print(", user_id = " + userId);
                count = resultSet.getBigDecimal(4);
                System.out.print(", count = " + count);
                resultPrice = resultSet.getBigDecimal(5);
                System.out.print(", result price = " + resultPrice);
                System.out.println();
                CurrentCheque currentCheck = new CurrentCheque(id, productId, userId, count, resultPrice);
                System.out.println("kkkkkkkkkkkkkk" + currentCheck.getProductID());
                System.out.println("kkkkkkkkkkkkkk" + currentCheck.getCount());
                System.out.println("ffkkkkkkkkkk" + currentCheck.getResultPrice());
                list.add(currentCheck);
                for(CurrentCheque q: list){
                    System.out.println("ffffffffffffff" + q.getProductID());
                    System.out.println("fffffffffffff" + q.getCount());
                    System.out.println("fffffffffffff" + q.getResultPrice());
                }
            }
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in getting opened list");
            sqlEx.printStackTrace();
        }
        return list;
    }
    public void closeCheque(Connection connection, int userId){
        int count = 0;
        BigDecimal checkAmount = new BigDecimal(0);
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM cash_register.current_check WHERE current_check.user_id = ?");
            pStatement.setInt(1, userId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                checkAmount = checkAmount.add(resultSet.getBigDecimal(5));
                count++;
            }

            ChequeReports checkReports = new ChequeReports(userId, count, checkAmount);
            String sqlInsert = "INSERT INTO cash_register.check_reports (user_id, number_of_product, check_amount, time_close) VALUES (?,?,?,NOW())";
            PreparedStatement prStatement = connection.prepareStatement(sqlInsert);
            prStatement.setInt(1, checkReports.getUserId());// userId
            prStatement.setInt(2, checkReports.getNumberOfProduct());//count
            prStatement.setBigDecimal(3, checkReports.getChequeAmount());//checkAmount
            prStatement.executeUpdate();
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in close");
            sqlEx.printStackTrace();
            return;
        }
    }*/
}
