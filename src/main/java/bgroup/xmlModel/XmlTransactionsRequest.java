package bgroup.xmlModel;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by VSB on 26.12.2017.
 * cleverClients
 */
public class XmlTransactionsRequest {
    /**
     * CARD_NUM=1234567
     * TYPE=select
     * START_DATE = 2011-09-01
     * END_DATE = 2011-09-01
     * KEY=MD5(CARD_NUM + SECRET_WORD)
     */
    private String cardNumber;
    private String type;
    private String startDate;
    private String endDate;
    private String key;

    public XmlTransactionsRequest() {
    }

    public XmlTransactionsRequest(String cardNumber, String type, String startDate, String endDate, String key) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.key = key;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "TransactionsRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", type='" + type + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String toXmlString() {
        return null;
    }

    public String toRequestString() {
        return "CARD_NUM=" + cardNumber + "&TYPE=" + type + "&START_DATE=" + startDate +
                "&END_DATE=" + endDate + "&KEY=" + DigestUtils.md5Hex(cardNumber + key);
    }
}
