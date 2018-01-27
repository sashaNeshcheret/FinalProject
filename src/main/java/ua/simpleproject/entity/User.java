package ua.simpleproject.entity;

import java.math.BigDecimal;

public class User {
    private int id;
    private String position;
    private String name;
    private String login;
    private String password;
    private String checkWord;
    private BigDecimal salary;
    private int numberMistake;
    private int experience;

    public User() {
    }

    public User(String position, String name, String login, String password, String checkWord) {
        this.position = position;
        this.name = name;
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
    public int getNumberMistake() {
        return numberMistake;
    }
    public void setNumberMistake(int numberMistake) {
        this.numberMistake = numberMistake;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
