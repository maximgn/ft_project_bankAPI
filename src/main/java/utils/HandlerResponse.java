package response.parse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Accounts;
import responses.Response;

public class HandlerResponse {

    public static String responseToJSON(Response response) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public static Accounts requestToEntity(String request) {
        Accounts entity = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            entity = mapper.readValue(request, Accounts.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return entity;
    }
}
