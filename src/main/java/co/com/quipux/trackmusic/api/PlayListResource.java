/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.api;

import co.com.quipux.trackmusic.ejb.PlayListFacade;
import co.com.quipux.trackmusic.entities.PlayList;
import co.com.quipux.trackmusic.entities.json.ResponseMessage;
import co.com.quipux.trackmusic.entities.json.ResponseMessageListElement;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.jwt.Claim;

/**
 *
 * @author mmarsiglia
 */
@Path("lists")
@RolesAllowed({"USER"})
@RequestScoped
public class PlayListResource {
    
    private static final Logger log = LogManager.getLogger(PlayListResource.class);
    
    @Inject
    private Validator validator;
    
    @Inject
    @Claim("jti")
    private String idUser;
    
    @Inject
    @Claim("aud")
    private String username;
    
    @EJB
    private PlayListFacade playListFacade;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayList(PlayList playList) throws GeneralSecurityException, IOException {
        
        log.debug("hit /lists \n" + playList.toString());
        
        Set<ConstraintViolation<PlayList>> result = validator.validate(playList);
        if (!result.isEmpty())
            return Response.status(400).build();
        else{
            
            playList.setUsuario(Integer.parseInt(idUser));
            playListFacade.setPlayList(playList);
            
            ResponseMessage msg = new ResponseMessage();
            msg.setService("/lists");
            msg.setMessage(ResponseMessage.MESSAGE_SUCCESS);
            msg.setBody(playList);
            
            return Response.ok(msg.toJSON()).status(201).build();
        }
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists() throws ParseException, IOException {
        
        log.debug("hit /lists");
        
        ResponseMessage msg = new ResponseMessage();
        msg.setService("/lists");
        
        List<PlayList> data = playListFacade.getPlayLists(idUser);
        msg.setMessage(ResponseMessage.MESSAGE_SUCCESS);
        msg.setBody(new ResponseMessageListElement(data.size(), data));
        
        return Response.ok(msg.toJSON()).build();
        
    }
    
    @GET
    @Path("/{listName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayLists(@PathParam("listName") String listName) throws ParseException, IOException {
        
        log.debug("hit /lists");
        
        ResponseMessage msg = new ResponseMessage();
        msg.setService("/lists");
        
        List<PlayList> data = playListFacade.getPlayLists(idUser, listName);
        if(data.isEmpty())
            return Response.status(404).build();
        else{
            msg.setMessage(ResponseMessage.MESSAGE_SUCCESS);
            msg.setBody(new ResponseMessageListElement(data.size(), data));
            return Response.ok(msg.toJSON()).build();
        }
        
    }
    
    @DELETE
    @Path("/{listName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("listName") String listName) throws ParseException {
        
        log.debug("hit lists/{listName}" + listName);
        
        ResponseMessage msg = new ResponseMessage();
        msg.setService("lists/{listName}");
        
        if(listName != null && !listName.isEmpty()){
            boolean removed = playListFacade.removePlayListByName(idUser, listName);
            if(removed) return Response.status(204).build();
            else return Response.status(404).build();
        }else
            return Response.status(404).build();
        
    }
    
}
