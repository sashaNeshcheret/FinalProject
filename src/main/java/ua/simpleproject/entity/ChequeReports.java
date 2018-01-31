package ua.simpleproject.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class ChequeReports {
    private int id;
    private int userId;
    private int numberOfProduct;
    private BigDecimal chequeAmount;
    private Timestamp date;

    public ChequeReports() {
    }

    public ChequeReports(int id, int userId, int numberOfProduct, BigDecimal chequeAmount, Timestamp date) {
        this.id = id;
        this.userId = userId;
        this.numberOfProduct = numberOfProduct;
        this.chequeAmount = chequeAmount;
        this.date = date;
    }

    public ChequeReports(int userId, int numberOfProduct, BigDecimal chequeAmount) {
        this.userId = userId;
        this.numberOfProduct = numberOfProduct;
        this.chequeAmount = chequeAmount;
    }

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getNumberOfProduct() {
        return numberOfProduct;
    }
    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }
    public Timestamp getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
    public void setChequeAmount(BigDecimal chequeAmount) {
        this.chequeAmount = chequeAmount;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
}
