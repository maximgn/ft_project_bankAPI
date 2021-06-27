package services;

import dao.CardsDAOImpl;
import entities.Account;
import entities.Card;
import utils.CardNumbers;

import java.sql.SQLException;
import java.util.Map;

public class CardService {

    private static CardService instance;
    private CardsDAOImpl dc;

    private CardService(){}

    public static CardService getInstance() {
        if (instance == null) {
            instance = new CardService();
        }
        return instance;
    }

    public void setCardsDAOImpl(CardsDAOImpl dc) {
        this.dc = dc;
    }

    public CardsDAOImpl getCardsDAOImpl() {
        return this.dc;
    }


    public Response add(String accountNumber) {
        CreateCardResponse response = new CreateCardResponse();
        try {
            Account account = dc.checkAccount(accountNumber);
            if (account == null) {
                response.setStatusCode(400);
                response.setResponse(null);
                return response;
            }

            try {
                Card newCard = new Card(generateNewCard(), account.getAccountNumber(), account.getUserID());
                String cardNumber = dc.create(newCard);
                response.setStatusCode(200);
                response.setResponse(cardNumber);
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

    public Response getAll(String accountNumber) {
        ReadCardsResponse response = new ReadCardsResponse();
        try {
            Account account = dc.checkAccount(accountNumber);
            if (account == null) {
                response.setStatusCode(400);
                response.setResponse(null);
                return response;
            }

            try {
                Map<Integer, String> cardList;
                cardList = dc.getAllByAccountNumber(accountNumber);
                response.setStatusCode(200);
                response.setResponse(cardList);
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
