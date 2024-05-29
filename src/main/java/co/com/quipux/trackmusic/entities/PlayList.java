/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author mmarsiglia
 */
@Entity
@Table(name = "playlist")
public class PlayList implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbTransient
    private Long id;
    
    @NotBlank(message = "nombre must not be empty")
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @JsonbTransient
    @Column(name = "usuario")
    private int usuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist", fetch = FetchType.LAZY)
    private List<Song> canciones;
    
    public PlayList(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getUsuario() {
        return usuario;
    }
    
    @Override
    public String toString() {
        return "co.com.quipux.trackmusic.entities.PlayList[ id=" + id + " ]";
    }
    
}
