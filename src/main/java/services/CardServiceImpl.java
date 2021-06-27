package services;

import dao.CardsDAOImpl;
import entities.Account;
import entities.Card;
import entities.Response;
import utils.CardNumbers;

import java.sql.SQLException;
import java.util.Map;

public class CardServiceImpl implements CardsService {

    private static CardServiceImpl instance;
    private CardsDAOImpl dc;

    private CardServiceImpl(){}

    public static CardServiceImpl getInstance() {
        if (instance == null) {
            instance = new CardServiceImpl();
        }
        return instance;
    }

    public void setCardsDAOImpl(CardsDAOImpl dc) {
        this.dc = dc;
    }

    public CardsDAOImpl getCardsDAOImpl() {
        return this.dc;
    }


    public Response createNewCardByAccountNumber(String accountNumber) {
        Response response = new Response();
        try {
            Account account = dc.checkAccount(accountNumber);
            if (account == null) {
                response.setStatus(400);
                response.setMessage("The account you provided does not exist.");
                response.setResponse(null);
                return response;
            }

            try {
                Card newCard = new Card(generateNewCard(), account.getAccountNumber(), account.getUserID());
                String cardNumber = dc.create(newCard);
                response.setStatus(200);
                response.setMessage("Card was added successfully.");
                response.setResponse(cardNumber);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatus(500);
                response.setMessage("Failed to connect to database.");
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatus(500);
            response.setMessage("Failed to connect to database.");
            response.setResponse(null);
            return response;
        }
        return response;
    }

    public Response getAllCards(String accountNumber) {
        Response response = new Response();
        try {
            Account account = dc.checkAccount(accountNumber);
            if (account == null) {
                response.setStatus(400);
                response.setMessage("The account you provided does not exist.");
                response.setResponse(null);
                return response;
            }

            try {
                Map<Integer, String> cardList;
                cardList = dc.getAllByAccountNumber(accountNumber);
                response.setStatus(200);
                response.setMessage("Cards was received successfully.");
                response.setResponse(cardList);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                response.setStatus(500);
                response.setMessage("Failed to connect to database.");
                response.setResponse(null);
                return response;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setStatus(500);
            response.setMessage("Failed to connect to database.");
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
