//package dao;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import services.AccountServiceImpl;
//import utils.ReadContentFile;
//
//import java.math.BigDecimal;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static utils.DatabaseConnection.createOrFillDatabase;
//
//class AccountsDAOImplTest {
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
//    void readBalance() {
//        String accountNumber = "10100202139087645632";
//        BigDecimal expectedBalance = new BigDecimal(200000);
//        BigDecimal balance = null;
//        try {
//            balance = AccountsDAOImpl.getInstance().readBalance(accountNumber);
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//        }
//        assertEquals(expectedBalance, balance);
//    }
//
//    @Test
//    void updateBalance() {
//        String accountNumber = "10100202139087645632";
//        BigDecimal expectedBalance = new BigDecimal(200000);
//        BigDecimal balanceAfterUpdate = new BigDecimal(0);
//        try {
//            AccountsDAOImpl.getInstance().updateBalance(accountNumber, 0);
//            balanceAfterUpdate = AccountsDAOImpl.getInstance().readBalance(accountNumber);
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//        }
//        assertEquals(expectedBalance, balanceAfterUpdate);
//    }
//
////    @AfterAll
//
//}