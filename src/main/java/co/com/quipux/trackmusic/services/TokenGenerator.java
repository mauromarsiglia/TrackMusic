/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.services;

import co.com.quipux.trackmusic.entities.UserSimple;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

/**
 *
 * @author mmarsiglia
 */
@ApplicationScoped
public class TokenGenerator {
    
    @Inject
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String issuer;
    
    public String generateFor(UserSimple user){
        try{
            SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .keyID("/privateKey.pem")
                    .type(JOSEObjectType.JWT).build(), JWTClaimsSet.parse(generateJWT(user).toString()));
            signedJWT.sign(new RSASSASigner(readPrivateKey("/META-INF/keys/privateKey.pem")));
            return signedJWT.serialize();
        } catch(Exception ex){
            throw new RuntimeException("Failed generating JWT", ex);
        }
    }

    private JsonObject generateJWT(UserSimple user) {
        if(user.getEmail()==null){
            user.setEmail("nomail");
        }
        long secondsNow = System.currentTimeMillis() / 1_000;
        List<String> roles = new LinkedList<>();
        roles.add("USER");
        JsonObject json = Json.createObjectBuilder()
                .add(Claims.aud.name(), user.getName()+" "+user.getLastname())
                .add(Claims.jti.name(), user.getId().toString())
                .add(Claims.sub.name(), user.getName())
                .add(Claims.upn.name(), user.getLastname())
                .add(Claims.exp.name(), secondsNow + 25_929_000) // Expires every 30 days
                .add(Claims.iat.name(), secondsNow)
                .add(Claims.iss.name(), issuer)
                .add(Claims.auth_time.name(), secondsNow)
                .add(Claims.groups.name(), Json.createArrayBuilder(roles).build())
                .build();
        System.out.println(json.toString());
        return json;
    }

    private static PrivateKey readPrivateKey(String resourceName) throws Exception {
        return KeyFactory.getInstance("RSA")
                         .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(readKey(resourceName))));
    }
    
    private static String readKey(String resourceName) throws Exception {
        byte[] byteBuffer = new byte[16384]; 
        int length = Thread.currentThread().getContextClassLoader()
                .getResource(resourceName)
                .openStream()
                .read(byteBuffer);
        String key = new String(byteBuffer, 0, length).replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)----", "")
                .replaceAll("\r\n", "")
                .replaceAll("\n", "")
                .trim();
        return key;
    }
    
}
