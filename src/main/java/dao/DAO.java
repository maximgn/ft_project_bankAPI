package dao;

import entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.DatabaseConnection.getConnection;

public interface DAO {
    default Account checkAccount(String accountNumber) throws SQLException {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM ACCOUNTS WHERE account_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setAccountNumber(rs.getString("account_number"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setUserID(rs.getInt("user_id"));
                return account;
            }
        }
        return null;
    };
}
