package dao;
import entities.Card;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static utils.DatabaseConnection.getConnection;

public class CardsDAOImpl implements CardsDAO {

    private static CardsDAOImpl instance;
    private CardsDAOImpl(){}

    public static CardsDAOImpl getInstance() {
        if (instance == null) {
            instance = new CardsDAOImpl();
        }
        return instance;
    }

    @Override
    public String create(Card card) throws SQLException {
        Connection conn = getConnection();
        String SQL = "INSERT INTO CARDS VALUES(default,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getAccountNumber());
            stmt.setInt(3, card.getUserID());
            stmt.executeUpdate();
        }
        return card.getCardNumber();
    }

    @Override
    public Map<Integer, String> getAllByAccountNumber(String accountNumber) throws SQLException {
        Connection conn = getConnection();
        String SQL = "SELECT * FROM CARDS WHERE acc_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            Map<Integer, String> cardsList = new HashMap<>();
            while (rs.next()) {
                cardsList.put(rs.getInt("id"), rs.getString("card_number"));
            }
            return cardsList;
        }
    }

}
