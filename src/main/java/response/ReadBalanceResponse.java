package response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class ReadBalanceResponse implements Response<BigDecimal> {
    @JsonIgnore
    private int statusCode;
    private BigDecimal response;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setResponse(BigDecimal response) {
        this.response = response;
    }

    @Override
    public BigDecimal getResponse() {
        return this.response;
    }
}
