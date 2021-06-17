package dao;
import entities.Cards;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.DatabaseConnection.getConnection;

public class DataCards implements CardsDAO {

    private static DataCards instance;
    private DataCards(){}

    public static DataCards getInstance() {
        if (instance == null) {
            instance = new DataCards();
        }
        return instance;
    }

    @Override
    public void insert(Cards card) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String SQL = "INSERT INTO CARDS VALUES(default,?,?,?)";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getAccountNumber());
            stmt.setInt(3, card.getUserID());
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

    @Override
    public List<Cards> getAllCardsByAccountNumber(String accNumber) throws SQLException {
        List<Cards> allCardsByAccount = new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM CARDS WHERE acc_number = ?";
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, accNumber);
            rs = stmt.executeQuery();
            while (rs.next()) {
                allCardsByAccount.add(new Cards(rs.getInt("id"), rs.getString("card_number"), rs.getString("acc_number"), rs.getInt("user_id")));
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

        if (allCardsByAccount.size() == 0) return null;
        return allCardsByAccount;
    }

}
