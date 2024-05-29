/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities.json;

import java.util.List;

/**
 *
 * @author mmarsiglia
 */
public class ResponseMessageListElement implements JSONSerializeble {
    
    /**
     * @return the total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @return the data
     */
    public List getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List data) {
        this.data = data;
    }

    private Long total;
    private List data;

    public ResponseMessageListElement() {

    }

    public ResponseMessageListElement(Long total, List data) {
        this.total = total;
        this.data = data;
    }

    public ResponseMessageListElement(int total, List data) {
        this.total = Long.valueOf(total);
        this.data = data;
    }
    
}
