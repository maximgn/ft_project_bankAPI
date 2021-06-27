package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import entities.Response;
import services.AccountServiceImpl;
import services.CardServiceImpl;
import utils.HandlerResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private AccountServiceImpl accountServiceImpl;
    private CardServiceImpl cardServiceImpl;

    HttpServer server;

    public void setAccountsService(AccountServiceImpl as) {
        this.accountServiceImpl = as;
    }
    public void setCardsService(CardServiceImpl cs) {
        this.cardServiceImpl = cs;
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
            String URI = exchange.getRequestURI().getPath();
            String method = exchange.getRequestMethod();
            String bodyStr = "";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                bodyStr = br.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String, Object> body = null;
            if (!method.equals("GET")) body = HandlerResponse.requestToMap(bodyStr);

            Response response = null;

            if (URI.equals("/cards") && method.equals("POST")) {
                response = cardServiceImpl.createNewCardByAccountNumber((String)body.get("accountNumber"));
            }
            if (URI.split("/")[1].equals("cards") && method.equals("GET")) {
                response = cardServiceImpl.getAllCards(URI.split("/")[2]);
            }
            if (URI.equals("/accounts") && method.equals("PUT")) {
                response = accountServiceImpl.updateBalance((String)body.get("accountNumber"), (String)body.get("sum"));
            }
            if (URI.split("/")[1].equals("accounts") && method.equals("GET")) {
                response = accountServiceImpl.readBalance(URI.split("/")[2]);
            }
            if (response == null) {
                response = new Response();
                response.setStatus(400);
                response.setMessage("Invalid request");
                response.setResponse("Bad request");
            }

            handleResponse(exchange, response);
        }

        private void handleResponse(HttpExchange httpExchange, Response response) throws IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(response.getStatus(), HandlerResponse.responseToJSON(response).length());
            outputStream.write(HandlerResponse.responseToJSON(response).getBytes());
            outputStream.close();
        }
    }

}
