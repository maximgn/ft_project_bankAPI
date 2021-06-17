package response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class ReadCardsResponse implements Response<Map<Integer, String>> {
    @JsonIgnore
    private int statusCode;
    private Map<Integer, String> response;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setResponse(Map<Integer, String> response) {
        this.response = response;
    }

    @Override
    public Map<Integer, String> getResponse() {
        return this.response;
    }
}
