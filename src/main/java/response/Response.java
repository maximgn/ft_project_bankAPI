package response;

import java.util.Map;

public interface Response<T> {
    void setStatusCode(int statusCode);
    int getStatusCode();
    void setResponse(T t);
    T getResponse();
}
