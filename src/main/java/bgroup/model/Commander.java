package bgroup.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VSB on 01.04.2018.
 * cleverClients
 */
@Entity
@Table(name = "commanders")
public class Commander implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Commander() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
