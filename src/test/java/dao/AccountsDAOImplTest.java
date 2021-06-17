package dao;

import entities.Account;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import services.AccountService;
import utils.ReadContentFile;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static utils.DatabaseConnection.createOrFillDatabase;
import static utils.DatabaseConnection.getConnection;

class AccountsDAOImplTest {

    @BeforeAll
    static void setUp() {
        String schema = "src/main/resources/schema.sql";
        String data = "src/main/resources/data.sql";
        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
        AccountService.getInstance().setAccountsDAOImpl(AccountsDAOImpl.getInstance());
    }

    @Test
    void readBalance() {
        String accountNumber = "10100202139087645632";
        BigDecimal expectedBalance = new BigDecimal(200000);
        BigDecimal balance = null;
        try {
            balance = AccountsDAOImpl.getInstance().readBalance(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        assertEquals(expectedBalance, balance);
    }

    @Test
    void updateBalance() {
        String accountNumber = "10100202139087645632";
        BigDecimal expectedBalance = new BigDecimal(200000);
        BigDecimal balanceAfterUpdate = new BigDecimal(0);
        try {
            AccountsDAOImpl.getInstance().updateBalance(accountNumber, 0);
            balanceAfterUpdate = AccountsDAOImpl.getInstance().readBalance(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        assertEquals(expectedBalance, balanceAfterUpdate);
    }

//    @AfterAll

}