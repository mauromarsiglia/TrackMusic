/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.ejb;

import co.com.quipux.trackmusic.entities.UserSimple;
import com.isesoft.commons.facade.AbstractFacade;
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
public class UserFacade extends AbstractFacade<UserSimple>{
    
    @PersistenceContext(unitName = "TrackMusicServicesPU")
    private EntityManager em;

    public UserFacade() {
        super(UserSimple.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public UserSimple getUserByEmail(String email, String password) {
        return (UserSimple) em.createNativeQuery("SELECT * FROM usersimple WHERE email = ?1 AND password = ?2 ORDER BY id ASC", UserSimple.class)
                .setParameter(1, email)
                .setParameter(2, password)
                .getResultList().stream().findFirst().orElse(null);
    }
    
}
