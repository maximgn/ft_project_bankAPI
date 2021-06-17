package dao;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface AccountsDAO extends DAO {
    Object updateBalance(String accountNumber, Object sum) throws SQLException;
    BigDecimal readBalance(String accountNumber) throws SQLException;
}
