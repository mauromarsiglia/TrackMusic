/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.api;

import co.com.quipux.trackmusic.entities.PlayList;
import co.com.quipux.trackmusic.entities.json.ResponseMessage;
import co.com.quipux.trackmusic.entities.json.ResponseMessageListElement;
import co.com.quipux.trackmusic.entities.response.Token;
import co.com.quipux.trackmusic.services.SpotifyService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("track")
@RolesAllowed({"USER"})
@RequestScoped
public class SpotifyResource {
    
    private static final Logger log = LogManager.getLogger(SpotifyResource.class);
    
    @Inject
    private Validator validator;
    
    @Inject
    @Claim("jti")
    private String idUser;
    
    @Inject
    @Claim("aud")
    private String username;
    
    @Inject
    private SpotifyService spotifyService;
    
    @GET
    @Path("/genres")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeners() throws ParseException, IOException, InterruptedException, ExecutionException {
        
        log.debug("hit /track/genres");
        
        ResponseMessage msg = new ResponseMessage();
        msg.setService("/track/genres");
        
        String token = spotifyService.getToken();
        List<String> geners = spotifyService.getGenres(token).get();
        
        msg.setMessage(ResponseMessage.MESSAGE_SUCCESS);
        msg.setBody(new ResponseMessageListElement(geners.size(), geners));
        
        return Response.ok(msg.toJSON()).build();
        
    }
    
}
