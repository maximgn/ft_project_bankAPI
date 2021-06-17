package services;

import dao.CardsDAOImpl;
import entities.responses.body.AllCardsResponseEntity;
import entities.Card;
import entities.responses.server.ShowAllCardsServerResponse;
import java.sql.SQLException;
import java.util.*;

public class CardsService {

    private static CardsService instance;
    private CardsDAOImpl dc;

    private CardsService(){}

    public static CardsService getInstance() {
        if (instance == null) {
            instance = new CardsService();
        }
        return instance;
    }

    public void setCardsDAOImpl(CardsDAOImpl dc) {
        this.dc = dc;
    }

    public CardsDAOImpl getCardsDAOImpl() {
        return this.dc;
    }

    public ShowAllCardsServerResponse showAllCards(String accountNumber) {
        ShowAllCardsServerResponse response = new ShowAllCardsServerResponse();
        List<Card> cardsList = null;

        try {
            cardsList = dc.getAllCardsByAccountNumber(accountNumber);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            response.setServerCode(500);
            response.setData(null);
            return response;
        }

        if (cardsList == null) {
            response.setServerCode(400);
            response.setData(null);
            return response;
        }

        response.setServerCode(200);
        Map<Integer, String> cardsMap = new HashMap<>();
        response.setData(new AllCardsResponseEntity(cardsMap));
        for(Card card : cardsList) {
            response.getData().getField().put(card.getId(), card.getCardNumber());
        }
        return response;
    }
}
