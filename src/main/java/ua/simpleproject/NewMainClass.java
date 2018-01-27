package ua.simpleproject;

import ua.simpleproject.validation.EnterDataValidator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewMainClass {
    /*private static Pattern pattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)");
*/
    private static String pattern = "(^";
    public static final String LOGIN_PATTERN = "[A-Za-z]+[A-Za-z0-9-_]+";
    public static final String PASSWORD_PATTERN = "[A-za-z0-9]{4}";


    public static void main(String[] arg){
        validTextField1("aaaa", PASSWORD_PATTERN, 20);
       // EnterDataValidator.isValidLogin("1");
    }
    private static boolean validTextField1(String word, String pattern, int length) {
        if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);
            //String found = matcher.group();

            //if(!Objects.equals(found, "") && !Objects.isNull(found) && Objects.equals(found, word))
            //    return true;

            if(matcher.matches()){
                return true;
            }
        }
        return false;
    }
}
