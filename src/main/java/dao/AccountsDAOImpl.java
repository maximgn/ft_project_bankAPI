package dao;

import entities.Accounts;
import java.math.BigDecimal;
import java.sql.*;

import static utils.DatabaseConnection.getConnection;

public class DataAccounts implements AccountsDAO {

    private static DataAccounts instance;
    private DataAccounts(){}

    public static DataAccounts getInstance() {
        if (instance == null) {
            instance = new DataAccounts();
        }
        return instance;
    }

    @Override
    public Accounts getAccountByNumber(String accNumber) throws SQLException {
        Accounts account = new Accounts();
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM ACCOUNTS WHERE account_number = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, accNumber);
            rs = stmt.executeQuery();
            if (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setAccountNumber(rs.getString("account_number"));
                account.setBalance(rs.getBigDecimal("balance"));
                account.setUserID(rs.getInt("user_id"));
                return account;
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public BigDecimal getAccountBalance(String accNumber) throws SQLException {
        BigDecimal balance = new BigDecimal(0);
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT balance FROM ACCOUNTS WHERE account_number = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, accNumber);
            rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getBigDecimal("balance");
            }
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return balance;
    }

    @Override
    public void updateBalanceAccount(String accNumber, Object sum) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            String SQL = "UPDATE ACCOUNTS SET balance = balance + ? WHERE account_number = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, sum);
            stmt.setString(2, accNumber);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }
}
