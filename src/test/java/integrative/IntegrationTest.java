package integrative;
import dao.AccountsDAOImpl;
import dao.CardsDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.AccountService;
import services.CardService;
import controller.Controller;
import utils.ReadContentFile;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static utils.DatabaseConnection.createOrFillDatabase;
import static utils.DatabaseConnection.getConnection;

public class IntegrationTest {

    @BeforeAll
    static void setUp() {
        String schema = "src/main/resources/schema.sql";
        String data = "src/main/resources/data.sql";
        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
        Controller controller = new Controller();
        AccountService.getInstance().setAccountsDAOImpl(AccountsDAOImpl.getInstance());
        CardService.getInstance().setCardsDAOImpl(CardsDAOImpl.getInstance());
        controller.setAccountsService(AccountService.getInstance());
        controller.setCardsService(CardService.getInstance());
        controller.runServer();
    }

    @Test
    public void requestOnAddNewCardTest() throws Exception {

        int expectedStatusCode = 200;
        String requestBody = "{\"accountNumber\":\"10100202139087645632\"}";
        HttpClient client = HttpClient.newBuilder()
                            .proxy(ProxySelector.getDefault())
                            .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9099/cards/"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @Test
    public void requestOnCheckAllCards() throws Exception {
        int expectedServerCode = 200;
        String expectedBodyResponse = "{\"response\":{\"7\":\"4276390011223350\"}}";
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9099/cards/10100202139087645637"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(expectedServerCode, response.statusCode());
        assertEquals(expectedBodyResponse, response.body());
    }

    @Test
    public void requestOnIncreaseBalance() throws Exception {
        int expectedServerCode = 200;
        String expectedBodyResponse = "{\"response\":600122}";
        String requestBody = "{\"sum\":122}";
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9099/accounts/10100202139087645634"))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(expectedServerCode, response.statusCode());
        assertEquals(expectedBodyResponse, response.body());
    }

    @Test
    public void requestOnCheckBalance() throws Exception {
        int expectedServerCode = 200;
        String expectedBodyResponse = "{\"response\":765345}";
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.getDefault())
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9099/accounts/10100202139087645637"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(expectedServerCode, response.statusCode());
        assertEquals(expectedBodyResponse, response.body());
    }
}
