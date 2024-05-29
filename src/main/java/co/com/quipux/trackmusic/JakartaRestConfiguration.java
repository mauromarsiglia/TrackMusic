package co.com.quipux.trackmusic;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;


@ApplicationPath("/")
@LoginConfig(authMethod = "MP-JWT", realmName = "MP-JWT")
@ApplicationScoped
public class JakartaRestConfiguration extends Application {
    
}
