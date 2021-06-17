package response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmptyResponse implements Response<String> {
    @JsonIgnore
    private int statusCode;
    private String response;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String getResponse() {
        return this.response;
    }
}
