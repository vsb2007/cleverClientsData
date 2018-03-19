package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VSB on 24.07.2017.
 * cleverClients
 */
@Entity
@Table(name = "TERMINAL")
public class Terminal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateWatch")
    private Date dateWatch;

    @Column(name = "dateFill")
    private Date dateFill;

    @Column(name = "smena")
    private Integer smena;

    @Column(name = "azs")
    private Integer azs;

    @Column(name = "orgId")
    private Integer orgId;

    @Column(name = "oilId")
    private Integer oilId;

    @Column(name = "vCO")
    private Double vCO;

    @Column(name = "vTerm")
    private Double vTerm;

    @Column(name = "prim")
    private String prim;

    @Column(name = "locked")
    private Integer locked;

    public Terminal() {
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

    public Integer getSmena() {
        return smena;
    }

    public void setSmena(Integer smena) {
        this.smena = smena;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public Double getvCO() {
        return vCO;
    }

    public void setvCO(Double vCO) {
        this.vCO = vCO;
    }

    public Double getvTerm() {
        return vTerm;
    }

    public void setvTerm(Double vTerm) {
        this.vTerm = vTerm;
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

    public Integer getAzs() {
        return azs;
    }

    public void setAzs(Integer azs) {
        this.azs = azs;
    }
}
