package ua.simpleproject.validation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EnterDataValidator {
    private static Logger LOGGER = Logger.getLogger(EnterDataValidator.class);

    //login
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9]*";
    //password
    public static final String PASSWORD_PATTERN = "[A-za-z0-9]+";
    //name
    public static final String NAME_PATTERN = "[A-Za-z ]*[А-Яа-я'\" ]*";

    //food name
    public static final String PRODUCT_NAME_PATTERN = "[A-ZА-Яa-zа-я' ]";

    public static boolean isValidLogin(String word){
        return validTextField(word, LOGIN_PATTERN, 56);
    }

    public static boolean isValidPassword(String word) {
        return validTextField(word, PASSWORD_PATTERN, 56);
    }
    public static boolean isValidName(String word){
        return validTextField(word, NAME_PATTERN, 56);
    }
    public static boolean isValidFoodName(String word){
        return validTextField(word, PRODUCT_NAME_PATTERN, 250);
    }


    private static boolean validTextField(String word, String pattern, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);
            String found = matcher.group();

            if(!Objects.equals(found, "") && !Objects.isNull(found) && Objects.equals(found, word))
                return true;

            if(matcher.matches()){
                return true;
            }
        }
        return false;
    }

}