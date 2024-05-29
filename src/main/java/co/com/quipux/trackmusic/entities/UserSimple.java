/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities;

import co.com.quipux.trackmusic.entities.json.JSONSerializeble;
import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author mmarsiglia
 */
@Entity
@Table(name = "users")
public class UserSimple implements Serializable, JSONSerializeble {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbTransient
    private Integer id;
    
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 6)
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 80)
    @Column(name = "password")
    @JsonbTransient
    private String password;
    @Column(name = "token")
    private String token;
    
    public UserSimple() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
}
