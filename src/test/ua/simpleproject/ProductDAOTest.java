package ua.simpleproject;

import org.apache.log4j.Logger;
import org.junit.*;
import ua.simpleproject.dao.DAOProduct;
import ua.simpleproject.dao.Impl.DAOFactory;
import ua.simpleproject.entity.Product;
import ua.simpleproject.exception.ConnectionException;
import ua.simpleproject.exception.DAOException;
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

public class ProductDAOTest {
    static final String TABLE_NAME = "products";
    static DAOProduct daoProduct;
    static ConnectionWrapper conn;

    static Logger log = Logger.getLogger(ProductDAOTest.class.getSimpleName());

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

        daoProduct = DAOFactory.getDaoFactory().getDaoProduct();
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
    public void readByIdTest() throws DAOException {
        Product product = daoProduct.readById(1);
        assertNotNull(product);
        assertEquals(1001, product.getCodeProduct());
        assertEquals("банани", product.getName());
        assertEquals(false, product.isCountable());
        assertEquals(new BigDecimal("33.55"), product.getPriceForOne());
    }
    @Test
    public void readTest() throws DAOException {
        Product product = daoProduct.read(1001);
        assertNotNull(product);
        assertEquals(1, product.getId());
        assertEquals("банани", product.getName());
        assertEquals(false, product.isCountable());
        assertEquals(new BigDecimal("33.55"), product.getPriceForOne());
    }
    @Test
    public void readByNameTest() throws DAOException {
        Product product = daoProduct.read("мандарини");
        assertNotNull(product);
        assertEquals(2, product.getId());
        assertEquals(1002, product.getCodeProduct());
        assertEquals(false, product.isCountable());
        assertEquals(new BigDecimal("28.35"), product.getPriceForOne());

    }
    @Test
    public void createTest() throws DAOException {
        Product productOld = new Product(5, 1003, "сливи", false, new BigDecimal(56.76));
        daoProduct.create(productOld);
        Product product = daoProduct.read("сливи");
        assertNotNull(product);
        assertEquals(5, product.getId());
        assertEquals(1003, product.getCodeProduct());
        assertEquals(false, product.isCountable());
        assertEquals(new BigDecimal("56.76"), product.getPriceForOne());

    }

}