package bgroup.jsonModel;

/**
 * Created by VSB on 12.12.2017.
 * cleverClients
 */
public class CardNumberRequest {
    private String cardNumberName;
    private String cardNumber;

    public CardNumberRequest() {
    }

    public CardNumberRequest(String cardNumberName, String cardNumber) {
        this.cardNumberName = cardNumberName;
        this.cardNumber = cardNumber;
    }

    public String getCardNumberName() {
        return cardNumberName;
    }

    public void setCardNumberName(String cardNumberName) {
        this.cardNumberName = cardNumberName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "CardNumberRequest{" +
                "cardNumberName='" + cardNumberName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }

    public String toJsonString() {
        return "{\"" + cardNumberName + "\" : \"" + cardNumber + "\"}";
    }
}
