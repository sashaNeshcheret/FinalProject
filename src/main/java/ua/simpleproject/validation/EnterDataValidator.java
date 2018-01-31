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
    public static final int PASSWORD_LENGTH = 4;
    //login
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9-_]+";
    //password
    public static final String PASSWORD_PATTERN = "[A-za-z]+[A-za-z0-9]{"+ PASSWORD_LENGTH +"}";
    //name
    public static final String NAME_PATTERN = "([A-Za-z]+( )?[^ ])+|([^ ][А-Яа-яї'\" ]+[^ ])";

    public static boolean isValidLogin(String word){
        return validTextField(word, LOGIN_PATTERN, 56);
    }
    public static boolean isValidPassword(String word) {
        return validTextField(word, PASSWORD_PATTERN, PASSWORD_LENGTH);
    }
    public static boolean isValidName(String word){
        return validTextField(word, NAME_PATTERN, 56);
    }
    public static boolean isValidFoodName(String word){
        return validTextField(word, NAME_PATTERN, 250);
    }
    private static boolean validTextField(String word, String pattern, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);
            if(matcher.matches()){
                return true;
            }
        }
        return false;
    }

}