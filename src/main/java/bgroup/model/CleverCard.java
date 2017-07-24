package bgroup.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "SEX", columnDefinition = "INT default 0")
    private Boolean sex;

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
    private String cardNumber;

    @Column(name = "dateFill")
    private Date dateFill;

    @Column(name = "operator_name_card_out")
    private String operatorNameCardOut;

    @NotEmpty
    @Column(name = "azs", columnDefinition = "INT default -1")
    private Integer azs;

    @NotEmpty
    @Column(name = "user_id", columnDefinition = "INT default -1")
    private Integer userId;

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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
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
