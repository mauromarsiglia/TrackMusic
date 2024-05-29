/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mmarsiglia
 */
@Entity
@Table(name = "song")
public class Song implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonbTransient
    private Long id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "artista")
    private String artista;
    
    @Column(name = "album")
    private String album;
    
    @Column(name = "anno")
    private int anno;
    
    @Column(name = "genero")
    private String genero;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist")
    private PlayList playlist;
    
    public Song(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPlaylist(PlayList playlist) {
        this.playlist = playlist;
    }

    public PlayList getPlaylist() {
        return playlist;
    }
    
    @Override
    public String toString() {
        return "co.com.quipux.trackmusic.entities.Song[ id=" + id + " ]";
    }
    
}
