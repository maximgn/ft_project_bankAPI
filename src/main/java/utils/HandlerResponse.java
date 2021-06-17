package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import response.Response;

import java.util.HashMap;
import java.util.Map;

public class HandlerResponse {

    public static String responseToJSON(Response<?> response) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    public static Map<String, Object> requestToMap(String request) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> details = new HashMap<>();
        try {
            details = mapper.readValue(request, HashMap.class);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return details;
    }
}
