/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.test;

import co.com.quipux.trackmusic.api.LoginResource;
import co.com.quipux.trackmusic.ejb.UserFacade;
import co.com.quipux.trackmusic.entities.json.Credentials;
import co.com.quipux.trackmusic.services.TokenGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.validation.Validator;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;

/**
 *
 * @author mmarsiglia
 */
public class LoginResourceTest {
    
    @InjectMocks
    private LoginResource loginResource;
    
    @Mock
    private Validator validator;
    
    @Mock
    private UserFacade userFacade;
    
    @Mock
    private TokenGenerator tokenGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginResource = new LoginResource();
        loginResource.setValidator(validator);
        loginResource.setUserFacade(userFacade);
        loginResource.setTokenGenerator(tokenGenerator);
    }

    @Test
    public void testLoginSuccess() throws GeneralSecurityException, IOException {
        
        Credentials credentials = new Credentials();
        credentials.setEmail("mauro.marsiglia@gmail.com");
        credentials.setPassword("D3v3l0p3rS3l3cc10n4d0");

        Response response = loginResource.login(credentials);
        System.out.println(response.toString());

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
    }

    @Test
    public void testLoginInvalidCredentials() throws GeneralSecurityException, IOException {
        
        Credentials credentials = new Credentials();
        credentials.setEmail("mauro.marsiglia@gmail.com");
        credentials.setPassword("1Q2w3e4r5t.");

        Response response = loginResource.login(credentials);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        
    }

    @Test
    public void testLoginUserNotFound() throws GeneralSecurityException, IOException {
        
        Credentials credentials = new Credentials();
        credentials.setEmail("otrousuario@gmail.com");
        credentials.setPassword("12345");

        Response response = loginResource.login(credentials);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
        
    }
    
}
