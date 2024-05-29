/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.api;

import co.com.quipux.trackmusic.ejb.UserFacade;
import co.com.quipux.trackmusic.entities.UserSimple;
import co.com.quipux.trackmusic.entities.json.Credentials;
import co.com.quipux.trackmusic.entities.json.ResponseMessage;
import co.com.quipux.trackmusic.services.TokenGenerator;
import co.com.quipux.trackmusic.utils.StringUtils;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mmarsiglia
 */
@Path("login")
@ApplicationScoped
public class LoginResource {
    
    private static final Logger log = LogManager.getLogger(LoginResource.class);
    
    @EJB
    private UserFacade userFacade;
    
    @Inject
    private Validator validator;
    
    @Inject
    private TokenGenerator tokenGenerator;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) throws GeneralSecurityException, IOException {
        
        log.debug("hit /login \n" + credentials.toString());
        ResponseMessage msg = new ResponseMessage();
        Set<ConstraintViolation<Credentials>> result = validator.validate(credentials);
        msg.setMessage(ResponseMessage.MESSAGE_BAD_CREDENCIALS);
        msg.setService("/login");
        
        if (!result.isEmpty()) {
            String validations = result.stream()
                    .map((ConstraintViolation<Credentials> val) -> val.getMessage())
                    .collect(Collectors.joining(","));
            msg.setBody(validations);
            msg.setMessage(ResponseMessage.MESSAGE_FIELD_ERRORS);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(msg.toJSON()).build();
        }else{
            String email = credentials.getEmail();
            String passowrd = StringUtils.sha256(credentials.getPassword());
            UserSimple user = userFacade.getUserByEmail(email, passowrd);
            if(user != null)
                return processResponseUser(user, msg);
            else{
                msg.setMessage(ResponseMessage.MESSAGE_BAD_CREDENCIALS);
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(msg.toJSON()).build();
            }
        }
        
    }
    
    private Response processResponseUser(UserSimple user, ResponseMessage msg) throws IOException {
        user.setToken(tokenGenerator.generateFor(user));
        msg.setBody(user);
        msg.setMessage(ResponseMessage.MESSAGE_SUCCESS);
        return Response.ok(msg.toJSON()).build();
    }
    
    @GET
    @Path("test")
    public String test() throws GeneralSecurityException, IOException {
        return "TrackMusic Services are alive!";
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }
    
}
