package dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.CardService;
import utils.ReadContentFile;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static utils.DatabaseConnection.createOrFillDatabase;
import static utils.DatabaseConnection.getConnection;

class CardsDAOImplTest {
    @BeforeAll
    static void setUp() {
        String schema = "src/main/resources/schema.sql";
        String data = "src/main/resources/data.sql";
        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
        CardService.getInstance().setCardsDAOImpl(CardsDAOImpl.getInstance());
    }

    @Test
    void getAllByAccountNumber() throws Exception {
        String accountNumber = "10100202139087645635";
        Map<Integer, String> expectedCardList = new HashMap();
        expectedCardList.put(4,"4276390011223347");
        expectedCardList.put(5,"4276390011223348");
        Map<Integer, String> responseCardList = CardService.getInstance().getCardsDAOImpl().getAllByAccountNumber(accountNumber);
        for(Integer key : responseCardList.keySet()) {
            assertEquals(expectedCardList.get(key), responseCardList.get(key));
        }
    }
}