package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VSB on 24.07.2017.
 * cleverClients
 */
@Entity
@Table(name = "SNOW")
public class Snow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dateWatch")
    private Date dateWatch;

    @Column(name = "dateFill")
    private Date dateFill;

    @Column(name = "azsId")
    private Integer azsId;

    @Column(name = "oilId")
    private Integer oilId;

    @Column(name = "v")
    private Double v;

    @Column(name = "commanderId")
    private Integer commanderId;


    @Column(name = "prim")
    private String prim;

    @Column(name = "target")
    private String target;

    @Column(name = "locked")
    private Integer locked;

    public Snow() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public Integer getOilId() {
        return oilId;
    }

    public void setOilId(Integer oilId) {
        this.oilId = oilId;
    }

    public Double getV() {
        return v;
    }

    public void setV(Double v) {
        this.v = v;
    }

    public Integer getCommanderId() {
        return commanderId;
    }

    public void setCommanderId(Integer commanderId) {
        this.commanderId = commanderId;
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
}
