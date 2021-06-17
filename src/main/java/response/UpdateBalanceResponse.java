package response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class UpdateBalanceResponse implements Response<Object> {
    @JsonIgnore
    private int statusCode;
    private Object response;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setResponse(Object response) {
        this.response = response;
    }

    @Override
    public Object getResponse() {
        return this.response;
    }
}
