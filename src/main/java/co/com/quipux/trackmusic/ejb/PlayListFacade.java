/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.ejb;

import co.com.quipux.trackmusic.entities.PlayList;
import co.com.quipux.trackmusic.entities.Song;
import com.isesoft.commons.facade.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mmarsiglia
 */
@Stateless
public class PlayListFacade extends AbstractFacade<PlayList>{
    
    @PersistenceContext(unitName = "TrackMusicServicesPU")
    private EntityManager em;

    public PlayListFacade() {
        super(PlayList.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void setPlayList(PlayList playList){
        for(Song cancion : playList.getCanciones()){
            cancion.setPlaylist(playList);
        }
        em.persist(playList);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PlayList> getPlayLists(String userId) {
        return em.createNativeQuery("SELECT * FROM playlist WHERE usuario = ?1 ORDER BY id ASC", PlayList.class)
                .setParameter(1, userId)
                .getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PlayList> getPlayLists(String userId, String listName) {
        return em.createNativeQuery("SELECT * FROM playlist WHERE usuario = ?1 and nombre = ?2 ORDER BY id ASC", PlayList.class)
                .setParameter(1, userId)
                .setParameter(2, listName)
                .getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean removePlayListByName(String userId, String listName){
        
        em.createNativeQuery(
                "DELETE FROM song WHERE playlist IN (SELECT id FROM playlist WHERE nombre = ?1 and usuario = ?2)")
                .setParameter(1, listName)
                .setParameter(2, userId)
                .executeUpdate();
        
        return em.createNativeQuery("DELETE FROM playlist WHERE nombre = ?1 and usuario = ?2")
                .setParameter(1, listName)
                .setParameter(2, userId)
                .executeUpdate() > 0;
    }
    
}
