package ua.simpleproject.services;

import ua.simpleproject.entity.ProductCurrentCheque;
import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddProductStockLogic {
    private final static DAOStock daoStock = DAOFactory.getDaoFactory().getDaoStock();
    private final static DAOProduct daoProduct = DAOFactory.getDaoFactory().getDaoProduct();

    /**Add product to stock
     *
     * @param product
     * @param number
     * @return
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public boolean addProductStock(Product product, BigDecimal number) throws DAOException {
        Product oldProduct = null;
        Stock stock = null;
        oldProduct = daoProduct.read(product.getCodeProduct());
        try {
            stock = daoStock.read(oldProduct);
            BigDecimal newNumber = number.add(stock.getNumber());
            stock.setNumber(newNumber);
            daoStock.update(stock);
        } catch (LogicException e) {
            daoStock.create(oldProduct, number);
        }
        return true;
    }
    /**Get list of products in stock by numer of its page
     *
     * @param numPage - number of page
     * @return - list of products in stock
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public List<ProductCurrentCheque> getStock(int numPage) throws DAOException {

        int start = (numPage-1)*10;
        int end = (numPage)*10-1;
        List<ProductCurrentCheque> list = new ArrayList<>();
        List<Stock> stockList = daoStock.read(start, end);
        for(Stock stock : stockList){
            ProductCurrentCheque productCurrentCheck = new ProductCurrentCheque();
            Product product = daoProduct.readById(stock.getProductId());
            productCurrentCheck.setNameProduct(product.getName());
            productCurrentCheck.setCodeProduct(product.getCodeProduct());
            productCurrentCheck.setNumberOfProduct(stock.getNumber());
            productCurrentCheck.setPriceForOne(product.getPriceForOne());
            list.add(productCurrentCheck);
        }
        return list;
    }
}
