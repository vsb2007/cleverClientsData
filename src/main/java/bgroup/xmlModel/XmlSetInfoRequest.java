package bgroup.xmlModel;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by VSB on 25.01.2018.
 * cleverClients
 */
public class XmlSetInfoRequest {
    /**
     * CARD_NUM=1234567
     * TYPE=setinfo
     * KEY=MD5(CARD_NUM + SECRET_WORD)
     * CARD_NUM,FNAME,MNAME,LNAME,BDATE,PHONE
     */
    private String cardNumber;
    private String fName;
    private String mName;
    private String lName;
    private String bDate;
    private String phone;
    private String type;
    private String key;

    public XmlSetInfoRequest() {
    }

    public XmlSetInfoRequest(String cardNumber, String fName, String mName, String lName, String bDate, String phone, String type, String key) {
        this.cardNumber = cardNumber;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.bDate = bDate;
        this.phone = phone;
        this.type = type;
        this.key = key;
    }

    @Override
    public String toString() {
        return "XmlSetInfoRequest{" +
                "cardNumber='" + cardNumber + '\'' +
                ", fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", lName='" + lName + '\'' +
                ", bDate='" + bDate + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String toXmlString() {
        return null;
    }

    //CARD_NUM,FNAME,MNAME,LNAME,BDATE,PHONE
    public String toRequestString() {
        return "CARD_NUM=" + cardNumber + "&TYPE=" + type + "&KEY=" + DigestUtils.md5Hex(cardNumber + key)
                + "&FNAME=" + fName
                + "&MNAME=" + mName
                + "&LNAME=" + lName
                + "&BDATE=" + bDate
                + "&PHONE=" + phone;
    }
}
