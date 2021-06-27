package services;

import entities.Response;

public interface CardsService {
    Response createNewCardByAccountNumber(String accountNumber);
    Response getAllCards(String accountNumber);
}
