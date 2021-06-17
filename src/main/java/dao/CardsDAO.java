package dao;

import entities.Card;

import java.sql.SQLException;
import java.util.Map;

public interface CardsDAO extends DAO {
    String create(Card card) throws SQLException;
    Map<Integer, String> getAllByAccountNumber(String accountNumber) throws SQLException;
}
