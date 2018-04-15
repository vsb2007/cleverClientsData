package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VSB on 24.07.2017.
 * cleverClients
 */
@Entity
@Table(name = "MONEY")
public class Money implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateWatch")
    private Date dateWatch;

    @Column(name = "dateFill")
    private Date dateFill;

    @Column(name = "azsId")
    private Integer azsId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "v")
    private Double v;

    @Column(name = "prim")
    private String prim;

    @Column(name = "locked")
    private Integer locked;

    public Money() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateWatch() {
        return dateWatch;
    }

    public void setDateWatch(Date dateWatch) {
        this.dateWatch = dateWatch;
    }

    public Date getDateFill() {
        return dateFill;
    }

    public void setDateFill(Date dateFill) {
        this.dateFill = dateFill;
    }

    public Integer getAzsId() {
        return azsId;
    }

    public void setAzsId(Integer azsId) {
        this.azsId = azsId;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
