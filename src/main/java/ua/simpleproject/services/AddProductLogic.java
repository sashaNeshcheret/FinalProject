package ua.simpleproject.services;

import org.apache.log4j.Logger;
import ua.simpleproject.dao.*;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.entity.User;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.exception.TransactionException;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddProductLogic {
    private static final Logger logger = Logger.getLogger(AddProductLogic.class);
    private DAOFactory daoFactory = DAOFactory.getDaoFactory();
    private DAOCurrentCheque daoCurrentCheque = daoFactory.getDaoCurrentCheque();




    /**according to ID check if exist a product in stock in enough quantity, if not than return message about error
     * if yes than perform transaction
     * less the amounts in stock
     * rise quantity of current check
     * in given objects create ProductCurrentCheque add it to list and transfer it in JSP
     *
     * @param name
     * @param codeStr
     * @param numberStr
     * @param login
     * @return list of products which depicted on JSP pages
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     * @throws LogicException if doesn't exist a product in stock in enough quantity
     */
    public List<ProductCurrentCheque> addProduct(String name, String codeStr, String numberStr, String login) throws DAOException, LogicException {
        Product product = null;
        AddProductLogic addProductLogic = new AddProductLogic();
        List<ProductCurrentCheque> productCurrentCheckList = new ArrayList<>();
        if (codeStr.equals("")) {
            if (!name.equals("")) {
                product = addProductLogic.defineIdProducts(name);
            }
        } else{
            int code = Integer.parseInt(codeStr);
            product = addProductLogic.defineIdProducts(code);
        }
        try{
            TransactionManager.beginTransaction();
            reduceOnStock(product, numberStr);
            addToCurrentCheque(product, numberStr, login);
            productCurrentCheckList = getCurrentCheque(login);
            TransactionManager.endTransaction();
        } catch (TransactionException | ConnectionException e) {
            try {
                TransactionManager.rollBack();
            } catch (SQLException sqlE) {
                logger.error("During transaction has done error and transaction was canceled");
            }
        }
        return productCurrentCheckList;
    }

    /**According to name define ID of product
     *
     * @param name product's name
     * @return product
     * @throws DAOException
     */
    public Product defineIdProducts(String name) throws DAOException {
        DAOProduct daoProduct = daoFactory.getDaoProduct();
        Product product = daoProduct.read(name);
        return  product;
    }

    /**
     * Define product according to code
     * @param code
     * @return object product
     * @throws DAOException
     */
    public Product defineIdProducts(int code) throws DAOException {
        DAOProduct daoProduct = daoFactory.getDaoProduct();
        Product product = daoProduct.read(code);
        return  product;
    }

    /**Delete product from stock
     *
     * @param product
     * @param numberStr
     * @throws LogicException in case if quantity in stock less than needed
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public void reduceOnStock(Product product, String numberStr) throws LogicException, DAOException {
        DAOStock daoStock = daoFactory.getDaoStock();
        Stock stock = daoStock.read(product);
        BigDecimal number = new BigDecimal(numberStr);
        if(stock.getNumber().compareTo(number) == 1){
            BigDecimal bigDecimal = stock.getNumber().subtract(number);
            stock.setNumber(bigDecimal);
            daoStock.update(stock);
        }
        else{
            throw new LogicException("Number products on stock lesser than you want to get");
        }

    }

    /**Add to current cheque information about products, its quantity and user
     *
     *
     * @param product
     * @param numberStr
     * @param login
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public boolean addToCurrentCheque(Product product, String numberStr, String login) throws DAOException {//треба кидати Exception замість boolean
        DAOUsers daoUser = daoFactory.getDaoUsers();
        BigDecimal number = new BigDecimal(numberStr);
        User user = null;
        List<CurrentCheque> list = daoCurrentCheque.read(login);

        user = daoUser.read(login);
        daoCurrentCheque.read(login);

        for(CurrentCheque currentCheque : list){
            if(currentCheque.getProductID() == product.getId()){
                CurrentCheque newCurrentCheque = new CurrentCheque();
                BigDecimal newCount = currentCheque.getCount().add(number);
                BigDecimal newResultPrice = newCount.multiply(product.getPriceForOne());

                newCurrentCheque.setUserId(currentCheque.getUserId());
                newCurrentCheque.setProductID(currentCheque.getProductID());
                newCurrentCheque.setCount(newCount);
                newCurrentCheque.setResultPrice(newResultPrice);
                daoCurrentCheque.update(newCurrentCheque);
                return true;
            }
        }

        CurrentCheque currentCheque = new CurrentCheque();
        currentCheque.setUserId(user.getId());
        currentCheque.setProductID(product.getId());
        currentCheque.setCount(number);
        currentCheque.setResultPrice(product.getPriceForOne().multiply(number));
        daoCurrentCheque.create(currentCheque);

        return true;
    }

    /**Get information about products in current cheque by login of user
     *
     * @param login
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<ProductCurrentCheque> getCurrentCheque(String login) {
        DAOProduct daoProduct = daoFactory.getDaoProduct();
        List<CurrentCheque> listCheck = null;
        try {
            listCheck = daoCurrentCheque.read(login);
        } catch (DAOException e) {
            logger.error(String.format("Exception in method daoCurrentCheque.read by login '%s'", login), e);
        }
        List<ProductCurrentCheque>  listProductsInCheck = new ArrayList<>();

        for(CurrentCheque currentCheque : listCheck){
            ProductCurrentCheque productCurrentCheck = new ProductCurrentCheque();
            Product product = null;
            try {
                product = daoProduct.readById(currentCheque.getProductID());
            } catch (DAOException e) {
                logger.error(String.format("Exception in method daoProduct.read by id '%s'", login), e);
            }
            productCurrentCheck.setNameProduct(product.getName());
            productCurrentCheck.setCodeProduct(product.getCodeProduct());
            productCurrentCheck.setNumberOfProduct(currentCheque.getCount());
            productCurrentCheck.setPriceForOne(product.getPriceForOne());
            productCurrentCheck.setResultPrice(currentCheque.getResultPrice());
            listProductsInCheck.add(productCurrentCheck);
        }
        return listProductsInCheck;
    }
}
