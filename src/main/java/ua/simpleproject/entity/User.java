package ua.simpleproject.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class User {
    private int id;
    private String position;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Date dateLastReport;
    private String checkWord;
    private BigDecimal salary;


    public User() {
    }

    public User(String position, String name,String surname, String login, String password, String checkWord) {
        this.position = position;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.checkWord = checkWord;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDateLastReport() {
        return dateLastReport;
    }
    public void setDateLastReport(Date dateLastReport) {
        this.dateLastReport = dateLastReport;
    }
    public String getCheckWord() {
        return checkWord;
    }
    public void setCheckWord(String checkWord) {
        this.checkWord = checkWord;
    }
    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
