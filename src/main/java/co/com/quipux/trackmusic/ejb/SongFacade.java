/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.ejb;

import co.com.quipux.trackmusic.entities.Song;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.isesoft.commons.facade.AbstractFacade;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author mmarsiglia
 */
@Stateless
public class SongFacade extends AbstractFacade<Song> {
    
    @PersistenceContext(unitName = "TrackMusicServicesPU")
    private EntityManager em;

    public SongFacade() {
        super(Song.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Song> getSongs() {
        return em.createNativeQuery("SELECT * FROM Song ORDER BY id ASC")
                .getResultList();
    }
    
    
}
