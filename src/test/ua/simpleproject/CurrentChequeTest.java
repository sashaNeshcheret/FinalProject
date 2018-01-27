package ua.simpleproject;

import org.apache.log4j.Logger;
import org.junit.*;
import ua.simpleproject.dao.DAOCurrentCheque;
import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.CurrentCheque;
import ua.simpleproject.entity.Product;
import ua.simpleproject.entity.Stock;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.exception.LogicException;
import ua.simpleproject.transactions.ConnectionPool;
import ua.simpleproject.transactions.ConnectionWrapper;
import ua.simpleproject.transactions.TransactionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static ua.simpleproject.H2Connections.H2_CONNECTION_POOL;
import static ua.simpleproject.InMemoryDBManager.executeSQLScriptsFromFile;

public class CurrentChequeTest {
    static final String TABLE_NAME = "current_check";
    static DAOCurrentCheque daoCurrentCheque;
    static ConnectionWrapper conn;

    static Logger log = Logger.getLogger(CurrentChequeTest.class.getSimpleName());

    @BeforeClass
    public static void BeforeClass() {

    }

    @AfterClass
    public static void AfterClass() {
    }

    @Before
    public void init() throws SQLException {

        ConnectionPool.getConnectionPool().setDataSource(H2_CONNECTION_POOL);
        try {
            conn = TransactionManager.getConnection();
        } catch (ConnectionException e) {
            log.error(e.getMessage());
        }
        executeSQLScriptsFromFile("src\\main\\resources\\CreateTestDB.sql");
        executeSQLScriptsFromFile("src\\main\\resources\\CreateTestDataDBCurrent.sql");
        daoCurrentCheque = DAOFactory.getDaoFactory().getDaoCurrentCheque();
    }

    @After
    public void after() {
        try {
            executeSQLScriptsFromFile("src\\main\\resources\\DeleteTestDataDB.sql");
            conn.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void createTest() throws DAOException, LogicException {
        CurrentCheque currentCheque = new CurrentCheque
                (5, 1, 13, new BigDecimal("8.250"), new BigDecimal("350.30"));
        daoCurrentCheque.create(currentCheque);
        List<CurrentCheque> list = daoCurrentCheque.read("best");
        assertNotNull(list);
        assertEquals(1, list.size());
    }
}