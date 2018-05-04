package cn.infisa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vin;
    private String username;

    public User(String sboot, String s) {
        this.username = sboot;
        this.vin = s;
    }

    public String getUsername() {
        return username;
    }

    public String getVin() {
        return vin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
