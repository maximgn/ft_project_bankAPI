//package services;
//
//import dao.AccountsDAOImpl;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import utils.ReadContentFile;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static utils.DatabaseConnection.createOrFillDatabase;
//
//class AccountServiceTest {
//
//    @BeforeAll
//    static void setUp() {
//        String schema = "src/main/resources/schema.sql";
//        String data = "src/main/resources/data.sql";
//        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
//        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
//        AccountServiceImpl.getInstance().setAccountsDAOImpl(AccountsDAOImpl.getInstance());
//    }
//
//    @Test
//    void setAccountsDAOImpl() {
//        assertNotNull(AccountServiceImpl.getInstance().getAccountsDAOImpl());
//    }
//
//    @Test
//    void getAccountsDAOImpl() {
//        assertNotNull(AccountServiceImpl.getInstance().getAccountsDAOImpl());
//    }
//
//    @Test
//    void updateBalance() {
//        String accountNumber = "10100202139087645632";
//        int expectedStatus = 200;
//        BigDecimal expectedResponse = new BigDecimal(200122);
//        Response<?> response = AccountServiceImpl.getInstance().updateBalance(accountNumber, new BigDecimal(122));
//        assertEquals(expectedStatus, response.getStatusCode());
//        assertEquals(expectedResponse, response.getResponse());
//    }
//
//    @Test
//    void readBalance() {
//        String accountNumber = "10100202139087645633";
//        int expectedStatus = 200;
//        BigDecimal expectedResponse = new BigDecimal(400000);
//        Response<?> response = AccountServiceImpl.getInstance().readBalance(accountNumber);
//        assertEquals(expectedStatus, response.getStatusCode());
//        assertEquals(expectedResponse, response.getResponse());
//    }
//}