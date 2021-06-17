package services;

import dao.*;
import entities.*;
import entities.responses.body.CheckBalanceResponseEntity;
import entities.responses.body.IncreaseBalanceResponseEntity;
import entities.responses.body.NewCardResponseEntity;
import entities.responses.server.AddNewCardServerResponse;
import entities.responses.server.IncreaseAccountBalanceServerResponse;
import entities.responses.server.ShowAccountBalanceServerResponse;
import utils.CardNumbers;

import java.math.BigDecimal;
import java.sql.SQLException;

public class AccountsService {

    private static AccountsService instance;
    private AccountsDAOImpl da;

    private AccountsService(){}

    public static AccountsService getInstance() {
        if (instance == null) {
            instance = new AccountsService();
        }
        return instance;
    }

    public void setAccountsDAOImpl(AccountsDAOImpl da) {
        this.da = da;
    }

    public AccountsDAOImpl getAccountsDAOImpl() {
        return this.da;
    }

    public AddNewCardServerResponse addNewCard(String accountNumber) {
        AddNewCardServerResponse response = new AddNewCardServerResponse();
        Account soughtAccount = null;

        try {
            soughtAccount = da.getAccountByNumber(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }

        if (soughtAccount == null) {
            response.setServerCode(404);
            response.setData(null);
            return response;
        }

        String cardNumber = generateNewCard();
        Card newCard = new Card(cardNumber, soughtAccount.getAccountNumber(), soughtAccount.getUserID());
        CardsDAOImpl dc = CardsDAOImpl.getInstance();

        try {
            dc.insert(newCard);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }
        response.setServerCode(200);
        response.setData(new NewCardResponseEntity(cardNumber));
        return response;
    }

    public IncreaseAccountBalanceServerResponse increaseAccountBalance(String accountNumber, Object sum) {
        IncreaseAccountBalanceServerResponse response = new IncreaseAccountBalanceServerResponse();
        Account soughtAccount = null;

        try {
            soughtAccount = da.getAccountByNumber(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }

        if (soughtAccount == null) {
            response.setServerCode(404);
            response.setData(null);
            return response;
        }

        try {
            da.updateBalanceAccount(accountNumber, sum);
        } catch (Exception sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }

        response.setServerCode(200);
        response.setData(new IncreaseBalanceResponseEntity(true));
        return response;
    }

    public ShowAccountBalanceServerResponse showAccountBalance(String accountNumber) {

        ShowAccountBalanceServerResponse response = new ShowAccountBalanceServerResponse();
        Account soughtAccount = null;
        BigDecimal balance = new BigDecimal(0);

        try {
           soughtAccount = da.getAccountByNumber(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }

        if (soughtAccount == null) {
            response.setServerCode(404);
            response.setData(null);
            return response;
        }

        try {
            balance = da.getAccountBalance(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
        }
        response.setServerCode(200);
        response.setData(new CheckBalanceResponseEntity(balance));
        return response;
    }

    public String generateNewCard() {
        String cardNumber = "";
        String[] codeOne = new String[]{"4276", "5484", "4000", "5486", "4831", "5216"};
        String[] codeTwo = new String[]{"3900", "3178", "6900", "6543", "2721", "4944"};
        cardNumber += codeOne[(int)(Math.random() * codeOne.length)];
        cardNumber += codeTwo[(int)(Math.random() * codeTwo.length)];
        for(int i = 0; i < 4; i++) {
            cardNumber += CardNumbers.codes.remove((int)(Math.random() * CardNumbers.codes.size()));
        }
        return cardNumber;
    }

}
