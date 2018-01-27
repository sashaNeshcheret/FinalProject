package ua.simpleproject.dao.Impl;


import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.*;
import java.util.Objects;

public class DAOProductImpl implements DAOProduct {
    private static final String SQL_SELECT_BY_Product_CODE = "SELECT * FROM products WHERE code_product = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM products WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO products (code_product, name, countable, price_for_one) VALUES (?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM products WHERE products.id > 0";


    protected DAOProductImpl() {
    }

    /**Select from BD product by ID
     *
     * @param id
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public Product readById(int id) throws DAOException {
        Product product = new Product();
        ResultSet resultSet = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                product.setId(resultSet.getInt(1));
                product.setCodeProduct(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setCountable(resultSet.getBoolean(4));
                product.setPriceForOne(resultSet.getBigDecimal(5));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method read(user id: '%d') has thrown an exception.", id), e);
        }
        return product;
    }

    /** Select from BD products by code
     *
     * @param code
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public Product read (int code) throws DAOException {
        Product product = null;
        ResultSet resultSet = null;
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_BY_Product_CODE);
            preparedStatement.setInt(1,code);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCodeProduct(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setCountable(resultSet.getBoolean(4));
                product.setPriceForOne(resultSet.getBigDecimal(5));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method read(code: '%d') has thrown an exception.", code), e);
        }
        return product;
    }

    /**Select from BD products by name
     *
     *
     * @param name
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public Product read(String name) throws DAOException {
        Product product = new Product();
        ResultSet resultSet = null;

        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_SELECT_BY_NAME);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println(resultSet);
                product.setId(resultSet.getInt(1));
                product.setCodeProduct(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setCountable(resultSet.getBoolean(4));
                product.setPriceForOne(resultSet.getBigDecimal(5));
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(String.format("Method read(code: '%s') has thrown an exception.", name), e);
        }
        return product;
    }

    /**Insert products in table "Products"
     *
     * @param product
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(Product product) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setInt(1, product.getCodeProduct());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setBoolean(3, product.isCountable());
            preparedStatement.setBigDecimal(4, product.getPriceForOne());
            boolean d = preparedStatement.execute();

        }catch (SQLException | ConnectionException e){
            throw new DAOException(String.format("Method read(user id: '%d') has thrown an exception.", product), e);
        }
    }
/*

    public ResultSet read(Connection connection, Product product){
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_Product_CODE);
            preparedStatement.setInt(1,product.getCodeProduct());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackT
    }
    public void show(Connection connection) {
        try {
            Statement statemenrace();
        }
        return resultSet;t = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cash_register.products WHERE products.id > 0");
            while (resultSet.next()){
                System.out.print("id = " + resultSet.getInt(1));
                System.out.print(", product code = " + resultSet.getInt(2));
                System.out.print(", name = " + resultSet.getString(3));
                System.out.print(", countable = " + resultSet.getBoolean(4));
                System.out.println();
            }
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in show");
            sqlEx.printStackTrace();
            return;
        }
    }
    public String show(Connection connection, int code) {
        String answer = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM cash_register.products WHERE code_product = ?");
            pStatement.setInt(1, code);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                answer = "id = " + resultSet.getInt(1) + ", product code = " + resultSet.getInt(2) +
                        ", name = " + resultSet.getString(3) + "\n";
            }
            System.out.println(answer);
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in show");
            sqlEx.printStackTrace();
            return null;
        }
        return answer;
    }
    public String show(Connection connection, String name) {
        String answer = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM cash_register.products WHERE name = ?");
            pStatement.setString(1, name);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                answer = "id = " + resultSet.getInt(1) + ", product code = " + resultSet.getInt(2) +
                        ", name = " + resultSet.getString(3) + "\n";
            }
        } catch (SQLException sqlEx) {
            System.out.println("Mistake in show");
            sqlEx.printStackTrace();
            return null;
        }
        return answer;
    }*/
}
