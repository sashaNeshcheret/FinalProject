package ua.simpleproject.dao.Impl;

import ua.simpleproject.dao.DAOSoldProducts;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.SoldProducts;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSoldProductsImpl implements DAOSoldProducts{
    private static final String SQL_SELECT_BY_Product_CODE = "SELECT * FROM products WHERE code_product = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM products WHERE name = ?";
    private static final String SQL_INSERT = "INSERT INTO sold_product (sold_product_id, product_id, count) VALUES (?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM products WHERE products.id > 0";

    protected DAOSoldProductsImpl() {
    }

    /**Insert products in table "sold_product"
     *
     * @param soldProducts
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void create(SoldProducts soldProducts) throws DAOException {
        try(ConnectionWrapper connection = TransactionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.preparedStatement(SQL_INSERT);
            preparedStatement.setInt(1, soldProducts.getSoldProductId());
            preparedStatement.setInt(2, soldProducts.getProductId());
            preparedStatement.setBigDecimal(3, soldProducts.getCount());
            preparedStatement.executeUpdate();

        }catch (SQLException | ConnectionException e){
            throw new DAOException("Method create has thrown an exception.", e);
        }
    }


    /**Select from BD product by ID
     *
     * @param id
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    /*
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
    }*/

    /** Select from BD products by code
     *
     * @param code
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    /*
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
    /*
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

    */
}
