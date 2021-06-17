package dao;

import entities.Accounts;

import java.math.BigDecimal;

public interface AccDAO {
    //create
    void insert(Accounts account);

    //read
    Accounts getAccountByNumber(String accNumber);
    BigDecimal getAccountBalance(String accNumber);

    //update
    void updateBalanceAccount(String accNUmber);

    //delete
    void deleteAccountById(int id);
}
