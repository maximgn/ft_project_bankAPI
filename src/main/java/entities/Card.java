package entities;

import java.util.Objects;

public class Card {

    private int id;
    private String cardNumber;
    private String accountNumber;
    private int userID;

    public Card(String cardNumber, String accountNumber, int userID) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.userID = userID;
    }

    public Card(int id, String cardNumber, String accountNumber, int userID) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card cards = (Card) o;
        return Objects.equals(cardNumber, cards.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
