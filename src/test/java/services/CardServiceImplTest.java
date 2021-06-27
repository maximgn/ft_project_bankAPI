package services;

import dao.CardsDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ReadContentFile;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static utils.DatabaseConnection.createOrFillDatabase;

class CardServiceTest {

    @BeforeAll
    static void setUp() {
        String schema = "src/main/resources/schema.sql";
        String data = "src/main/resources/data.sql";
        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
        CardService.getInstance().setCardsDAOImpl(CardsDAOImpl.getInstance());
    }

    @Test
    void setCardsDAOImpl() {
        assertNotNull(CardService.getInstance().getCardsDAOImpl());
    }

    @Test
    void getCardsDAOImpl() {
        assertNotNull(CardService.getInstance().getCardsDAOImpl());
    }

    @Test
    void add() {
        String accountNumber = "10100202139087645638";
        int expectedStatusCode = 200;
        Response<?> response = CardService.getInstance().add(accountNumber);
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Test
    void getAll() {
        String accountNumber = "10100202139087645638";
        Map<Integer, String> expectedCardList = new HashMap<Integer, String>();
        expectedCardList.put(8,"4276390011223351");
        int expectedStatusCode = 200;
        ReadCardsResponse response = (ReadCardsResponse) CardService.getInstance().getAll(accountNumber);
        assertEquals(expectedStatusCode, response.getStatusCode());
        for(Integer key : response.getResponse().keySet()) {
            assertEquals(expectedCardList.get(key), response.getResponse().get(key));
        }
    }

    @Test
    void generateNewCard() {
        String generatedCardNumber = CardService.getInstance().generateNewCard();
        assertEquals(16, generatedCardNumber.length());
    }

}