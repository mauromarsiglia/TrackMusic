/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities.json;

/**
 *
 * @author mmarsiglia
 */
public class ResponseMessage implements JSONSerializeble{
    
    public static final String MESSAGE_SUCCESS = "SUCCESS";
    
    public static final String MESSAGE_FIELD_ERRORS = "FIELDS VALIDATION FAILED";
    public static final String MESSAGE_BAD_CREDENCIALS = "INVALID CREDENTIALS";
    public static final String MESSAGE_NOT_FOUND = "NOT FOUND";

    
    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the body
     */
    public Object getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String service;
    public Object body;
    public String message;
    
}
