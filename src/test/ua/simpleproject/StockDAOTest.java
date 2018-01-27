package ua.simpleproject;

import org.apache.log4j.Logger;
import org.junit.*;
import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.DAOStock;
import ua.simpleproject.dao.Impl.DAOFactory;
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

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static ua.simpleproject.H2Connections.H2_CONNECTION_POOL;
import static ua.simpleproject.H2Connections.truncateTable;
import static ua.simpleproject.InMemoryDBManager.executeSQLScriptsFromFile;

public class StockDAOTest {
    static final String TABLE_NAME = "stock";
    static DAOStock daoStock;
    static ConnectionWrapper conn;

    static Logger log = Logger.getLogger(StockDAOTest.class.getSimpleName());

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
        executeSQLScriptsFromFile("src\\main\\resources\\CreateTestDataDBProduct.sql");
        daoStock = DAOFactory.getDaoFactory().getDaoStock();
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
        Product product = new Product(4, 1004, "груша", false, new BigDecimal(34.76));
        daoStock.create(product, new BigDecimal("200.300"));
        Stock stock = daoStock.read(product);
        assertNotNull(stock);
        assertEquals(4, stock.getProductId());
        assertEquals(new BigDecimal("200.300"), stock.getNumber());
    }
}