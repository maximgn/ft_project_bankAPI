package services;

import dao.*;
import entities.Account;
import response.ReadBalanceResponse;
import response.Response;
import response.UpdateBalanceResponse;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AccountService {

    private static AccountService instance;
    private AccountsDAOImpl da;

    private AccountService(){}

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void setAccountsDAOImpl(AccountsDAOImpl da) {
        this.da = da;
    }

    public AccountsDAOImpl getAccountsDAOImpl() {
        return this.da;
    }

    public Response updateBalance(String accountNumber, Object sum) {
        UpdateBalanceResponse response = new UpdateBalanceResponse();
        try {
            Account account = da.checkAccount(accountNumber);
            if (account == null) {
                response.setStatusCode(400);
                response.setResponse(null);
                return response;
            }

            try {
                Object responseSum = da.updateBalance(accountNumber, sum);
                response.setStatusCode(200);
                response.setResponse(responseSum);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatusCode(500);
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatusCode(500);
            response.setResponse(null);
            return response;
        }
        return response;
    }

    public Response readBalance(String accountNumber) {
        ReadBalanceResponse response = new ReadBalanceResponse();
        try {
            Account account = da.checkAccount(accountNumber);
            if (account == null) {
                response.setStatusCode(400);
                response.setResponse(null);
                return response;
            }

            try {
                BigDecimal responseSum = da.readBalance(accountNumber);
                response.setStatusCode(200);
                response.setResponse(responseSum);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatusCode(500);
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatusCode(500);
            response.setResponse(null);
            return response;
        }
        return response;
    }

}
