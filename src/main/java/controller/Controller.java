package controller;

import com.sun.net.httpserver.*;
import response.EmptyResponse;
import response.Response;
import services.*;
import utils.HandlerResponse;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private AccountService accountService;
    private CardService cardService;

    HttpServer server;

    public void setAccountsService(AccountService as) {
        this.accountService = as;
    }
    public void setCardsService(CardService cs) {
        this.cardService = cs;
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

            Response response = null;
            String URI = exchange.getRequestURI().getPath();
            String[] paths = URI.split("/");
            String method = exchange.getRequestMethod();
            String bodyStr = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
                bodyStr = br.lines().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String, Object> body = null;
            if (!method.equals("GET")) body = HandlerResponse.requestToMap(bodyStr);

            if (paths[1].equals("cards")) {
                switch (method) {
                    case "POST":
                        response = cardService.add((String)body.get("accountNumber"));
                        break;
                    case "GET":
                        response = cardService.getAll(paths[2]);
                        break;
                    default: break;
                }
            }

            if (paths[1].equals("accounts")) {
                switch (method) {
                    case "PUT":
                        response = accountService.updateBalance(paths[2], body.get("sum"));
                        break;
                    case "GET":
                        response = accountService.readBalance(paths[2]);
                        break;
                    default: break;
                }
            }

            if (response == null) {
                response = new EmptyResponse();
                response.setStatusCode(400);
                response.setResponse("Bad Request");
            }

            handleResponse(exchange, response);
        }

        private void handleResponse(HttpExchange httpExchange, Response<?> response)  throws  IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(response.getStatusCode(), HandlerResponse.responseToJSON(response).length());
            outputStream.write(HandlerResponse.responseToJSON(response).getBytes());
            outputStream.close();
        }
    }

}
