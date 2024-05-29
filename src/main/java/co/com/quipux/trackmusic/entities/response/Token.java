/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities.response;

import co.com.quipux.trackmusic.entities.json.JSONSerializeble;
import java.io.Serializable;

/**
 *
 * @author mmarsiglia
 */
public class Token implements JSONSerializeble, Serializable{
    
    private String access_token;
    private String token_type;
    private String expires_in;
    
    public Token(){}

    @Override
    public String toString() {
        return "{" + "accessToken=" + access_token + ", tokenType=" + token_type + "}";
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
    
}
