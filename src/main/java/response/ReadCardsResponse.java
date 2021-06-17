package response;

import java.util.Map;

public class GetAllCardsResponse implements Response<Map<Integer, String>> {
    private int statusCode;
    private Map<Integer, String> cardsList;

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setResponse(Map<Integer, String> cardsList) {
        this.cardsList = cardsList;
    }

    @Override
    public Map<Integer, String> getResponse() {
        return this.cardsList;
    }
}
