/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities.json;

import co.com.quipux.trackmusic.utils.StringUtils;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author mmarsiglia
 */
public class Credentials {
    
    @NotBlank(message = "email must not be empty")
    private String email;
   
    @NotBlank(message = "password  must not be empty")
    private String password;

    @Override
    public String toString() {
        return "Credentials{" + "email=" + email + " password: " + StringUtils.sha256(password) + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
