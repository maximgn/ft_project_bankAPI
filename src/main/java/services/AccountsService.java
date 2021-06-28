package services;

import entities.Response;

public interface AccountsService {
    Response updateBalance(String accountNumber, Object sum);
    Response readBalance(String accountNumber);
}
