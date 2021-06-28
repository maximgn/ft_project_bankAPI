package services;

import dao.*;
import entities.Account;
import entities.Response;

import java.math.BigDecimal;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountsService {

    private static AccountServiceImpl instance;
    private AccountsDAOImpl da;

    private AccountServiceImpl(){}

    public static AccountServiceImpl getInstance() {
        if (instance == null) {
            instance = new AccountServiceImpl();
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
        Response response = new Response();
        try {
            Account account = da.checkAccount(accountNumber);
            if (account == null) {
                response.setStatus(400);
                response.setMessage("The account you provided does not exist");
                response.setResponse(null);
                return response;
            }

            try {
                Object responseSum = da.updateBalance(accountNumber, sum);
                response.setStatus(200);
                response.setMessage("The balance was successfully updated");
                response.setResponse(responseSum);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatus(500);
                response.setMessage("Failed to connect to database");
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatus(500);
            response.setMessage("Failed to connect to database");
            response.setResponse(null);
            return response;
        }
        return response;
    }

    public Response readBalance(String accountNumber) {
        Response response = new Response();
        try {
            Account account = da.checkAccount(accountNumber);
            if (account == null) {
                response.setStatus(400);
                response.setMessage("The account you provided does not exist");
                response.setResponse(null);
                return response;
            }

            try {
                BigDecimal responseSum = da.readBalance(accountNumber);
                response.setStatus(200);
                response.setMessage("The balance was successfully received");
                response.setResponse(responseSum);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatus(500);
                response.setMessage("Failed to connect to database");
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatus(500);
            response.setMessage("Failed to connect to database");
            response.setResponse(null);
            return response;
        }
        return response;
    }

}
