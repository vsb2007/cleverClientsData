package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VSB on 24.07.2017.
 * cleverClients
 */
@Entity
@Table(name = "CLEVER_CARD")
public class CleverCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateBirth")
    private Date dateBirth;

    @Column(name = "SEX")
    private Integer sex;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "f")
    private String f;
    @Column(name = "i")
    private String i;
    @Column(name = "o")
    private String o;

    @Column(name = "email")
    private String email;

    @Column(name = "vendorAuto")
    private String vendorAuto;

    @Column(name = "card_number")
    private Integer cardNumber;

    @Column(name = "card_number_old")
    private Integer cardNumberOld;

    @Column(name = "dateFill")
    private Date dateFill;

    @Column(name = "operator_name_card_out")
    private String operatorNameCardOut;


    @Column(name = "azs", columnDefinition = "INT default -1")
    private Integer azs;


    @Column(name = "user_id", columnDefinition = "INT default -1")
    private Integer userId;

    public CleverCard() {
    }

    public Integer getCardNumberOld() {
        return cardNumberOld;
    }

    public void setCardNumberOld(Integer cardNumberOld) {
        this.cardNumberOld = cardNumberOld;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVendorAuto() {
        return vendorAuto;
    }

    public void setVendorAuto(String vendorAuto) {
        this.vendorAuto = vendorAuto;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getDateFill() {
        return dateFill;
    }

    public void setDateFill(Date dateFill) {
        this.dateFill = dateFill;
    }

    public String getOperatorNameCardOut() {
        return operatorNameCardOut;
    }

    public void setOperatorNameCardOut(String operatorNameCardOut) {
        this.operatorNameCardOut = operatorNameCardOut;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Integer getAzs() {
        return azs;
    }

    public void setAzs(Integer azs) {
        this.azs = azs;
    }
}
