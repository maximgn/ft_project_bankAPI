//package dao;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import services.CardServiceImpl;
//import utils.ReadContentFile;
//
//import java.util.HashMap;
//import java.util.Map;
//import static org.junit.jupiter.api.Assertions.*;
//import static utils.DatabaseConnection.createOrFillDatabase;
//
//class CardsDAOImplTest {
//    @BeforeAll
//    static void setUp() {
//        String schema = "src/main/resources/schema.sql";
//        String data = "src/main/resources/data.sql";
//        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
//        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
//        CardServiceImpl.getInstance().setCardsDAOImpl(CardsDAOImpl.getInstance());
//    }
//
//    @Test
//    void getAllByAccountNumber() throws Exception {
//        String accountNumber = "10100202139087645635";
//        Map<Integer, String> expectedCardList = new HashMap();
//        expectedCardList.put(4,"4276390011223347");
//        expectedCardList.put(5,"4276390011223348");
//        Map<Integer, String> responseCardList = CardServiceImpl.getInstance().getCardsDAOImpl().getAllByAccountNumber(accountNumber);
//        for(Integer key : responseCardList.keySet()) {
//            assertEquals(expectedCardList.get(key), responseCardList.get(key));
//        }
//    }
//}