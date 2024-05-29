/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.services;

import co.com.quipux.trackmusic.entities.response.Token;
import java.util.List;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Asynchronous;

/**
 *
 * @author mmarsiglia
 */
@ApplicationScoped
public class SpotifyService {
    
    private static final Logger log = LogManager.getLogger(SpotifyService.class);
    
    @Inject
    @ConfigProperty(name = "spotify.endpoint.token")
    private String spotifyEndpointToken;
    
    @Inject
    @ConfigProperty(name = "spotify.client.id")
    private String spotifyId;
    
    @Inject
    @ConfigProperty(name = "spotify.client.secret")
    private String spotifySecret;
    
    @Inject
    @ConfigProperty(name = "spotify.endpoint.genre")
    private String spotifyEndpointGenre;
    
    // @Retry(maxRetries = 5, delay = 4000)
    public String getToken() {
        
        Form form = new Form();
        form.param("grant_type", "client_credentials");
        form.param("client_id", spotifyId);
        form.param("client_secret", spotifySecret);
        
        Client client = ClientBuilder.newClient();
        Response response = client.target(spotifyEndpointToken)
                    .request()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        
        System.out.println("getToken response");
        System.out.println("response:" + response.toString());
        
        Token output = response.readEntity(Token.class);
        System.out.println(output.toString());
        
        response.close();
        client.close();
        
        return output.getAccess_token();
        
    }
    
    // @Retry(maxRetries = 5, delay = 2000)
    public Future<List<String>> getGenres(String token) {
        
        log.debug("getGenres()");
        System.out.println("getGenres");
        
        Client client = ClientBuilder.newClient();
        Response response = client.target(spotifyEndpointGenre)
                    .request()
                    .header("Authorization", "Bearer " + token)
                    .get();
        
        System.out.println("response: " + response.toString());
        
        JsonObject responseJson = response.readEntity(JsonObject.class);
        JsonArray genresArray = responseJson.getJsonArray("genres");
        
        
        
        // Error en: 
        List<String> genres = IntStream.range(0, genresArray.size())
                    .mapToObj(genresArray::getJsonString)
                    .map(JsonString::getString)
                    .collect(Collectors.toList());
        
        response.close();
        client.close();
        
        return CompletableFuture.completedFuture(genres);
        
    }
    
    @Asynchronous
    public Future<Boolean> asyncConditionJobAttachmentErrorHandler() {
        return CompletableFuture.completedFuture(true);
    }
    
}
