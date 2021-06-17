package utils;

import com.sun.net.httpserver.*;
import entities.responses.server.ServerResponse;
import services.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private AccountsService accountsService;
    private CardsService cardsService;

    HttpServer server;

    public void setAccountsService(AccountsService as) {
        this.accountsService = as;
    }

    public void setCardsService(CardsService cs) {
        this.cardsService = cs;
    }

    public void runServer() {
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 9099), 0);
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        server.createContext("/", new MyHttpHandler());
        server.setExecutor(null);
        server.start();
    }

    private class MyHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            ServerResponse<?> response = null;
            String URI = exchange.getRequestURI().getPath();

            String requestContentBody = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                requestContentBody = br.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String, Object> postData = HandlerResponse.requestToMap(requestContentBody);

            if (URI.equals("/new/card")) {
                response = accountsService.addNewCard((String)postData.get("accountNumber"));
            } else if (URI.equals("/all/cards")) {
                response = cardsService.showAllCards(((String)postData.get("accountNumber")));
            } else if (URI.equals("/increase/balance")) {
                response = accountsService.increaseAccountBalance((String)postData.get("accountNumber"), postData.get("sum"));
            } else if (URI.equals("/check/balance")) {
                response = accountsService.showAccountBalance((String)postData.get("accountNumber"));
            } else {
                response.setServerCode(400);
                response.setData(null);
            }
            handleResponse(exchange, response);
        }

        private void handleResponse(HttpExchange httpExchange, ServerResponse<?> response)  throws  IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(response.getServerCode(), HandlerResponse.responseToJSON(response.getData()).length());
            outputStream.write(HandlerResponse.responseToJSON(response.getData()).getBytes());
            outputStream.close();
        }
    }

}
