package ua.simpleproject;

import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.transactions.ConnectionWithOutPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;
import ua.simpleproject.validation.EnterDataValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public static final String PASSWORD_PATTERN = "[A-za-z]+[A-za-z0-9]{3}";
    public static final String NAME_PATTERN = "([A-Za-z]+( )?[^ ])+|([^ ][А-Яа-яї'\" ]+[^ ])";



    public static void main(String[] arg){
        //validTextField1("koko", NAME_PATTERN, 20);
       // EnterDataValidator.isValidLogin("1");
        try {
            read("best");
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }
    private static boolean validTextField1(String word, String pattern, int length) {
       /* if(!Objects.equals(word, "") && !Objects.isNull(word) && word.length() < length) {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(word);
            if(matcher.matches()){
                return true;
            }
        }*/
        return false;
    }
    /** Select objects in DB by user login
     *
     * @param login
     * @return return list of objects of current cheque
     * @throws DAOException this is own exception that combines exceptions which
     * happened during work with database
     */
    public static List<CurrentCheque> read(String login)  throws DAOException{
        String SQL_SELECT_BY_USER_LOGIN = "SELECT * FROM current_check LEFT JOIN current_check.user_id = users.userId LEFT JOIN users.login = ?";
        List<CurrentCheque> list = new ArrayList<>();
        try(Connection connection = ConnectionWithOutPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CurrentCheque currentCheque = new CurrentCheque();
                currentCheque.setId(resultSet.getInt(1));
                currentCheque.setProductID(resultSet.getInt(2));
                currentCheque.setUserId(resultSet.getInt(3));
                currentCheque.setCount(resultSet.getBigDecimal(4));
                currentCheque.setResultPrice(resultSet.getBigDecimal(5));
                System.out.println(currentCheque.getId());
                System.out.println(currentCheque.getProductID());
                System.out.println(currentCheque.getUserId());
                System.out.println(currentCheque.getCount());
                System.out.println(currentCheque.getResultPrice());
                System.out.println();
                list.add(currentCheque);
            }
        } catch (SQLException  sqlEx) {
            System.out.println("Mistake in getting opened list");
            sqlEx.printStackTrace();
        }
        return list;
    }
}
