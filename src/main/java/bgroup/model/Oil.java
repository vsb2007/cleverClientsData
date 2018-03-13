package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VSB on 24.07.2017.
 * cleverClients
 */
@Entity
@Table(name = "OIL")
public class Oil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "oil")
    private String oil;

    public Oil() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }
}
