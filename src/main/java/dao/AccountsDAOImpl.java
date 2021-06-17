package dao;

import java.math.BigDecimal;
import java.sql.*;

import static utils.DatabaseConnection.getConnection;

public class AccountsDAOImpl implements AccountsDAO {

    private static AccountsDAOImpl instance;
    private AccountsDAOImpl(){}

    public static AccountsDAOImpl getInstance() {
        if (instance == null) {
            instance = new AccountsDAOImpl();
        }
        return instance;
    }

    @Override
    public Object updateBalance(String accountNumber, Object sum) throws SQLException {
        Connection conn = getConnection();
        String SQL = "UPDATE ACCOUNTS SET balance = balance + ? WHERE account_number = ?";
        try(PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setObject(1, sum);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
        BigDecimal currentBalance = readBalance(accountNumber);
        return currentBalance;
    }

    @Override
    public BigDecimal readBalance(String accountNumber) throws SQLException {
        Connection conn = getConnection();
        String SQL = "SELECT balance FROM ACCOUNTS WHERE account_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getBigDecimal("balance");
        }
    }

}
