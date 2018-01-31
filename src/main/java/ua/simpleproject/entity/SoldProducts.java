package ua.simpleproject.entity;

import java.math.BigDecimal;

public class SoldProducts {

    private int id;
    private int soldProductId;
    private int productId;
    private BigDecimal count;

    public SoldProducts() {
    }

    public SoldProducts(int id, int soldProductId, int productId, BigDecimal count) {
        this.id = id;
        this.soldProductId = soldProductId;
        this.productId = productId;
        this.count = count;
    }

    public SoldProducts(int soldProductId, int productId, BigDecimal count) {
        this.soldProductId = soldProductId;
        this.productId = productId;
        this.count = count;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getSoldProductId() {
        return soldProductId;
    }
    public void setSoldProductId(int soldProductId) {
        this.soldProductId = soldProductId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public BigDecimal getCount() {
        return count;
    }
    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
