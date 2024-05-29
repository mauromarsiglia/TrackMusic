/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.quipux.trackmusic.entities.json;

import java.util.Locale;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;

/**
 *
 * @author mmarsiglia
 */
public interface JSONSerializeble {
    
    public default String toJSON() {

        // Custom configuration
        JsonbConfig config = new JsonbConfig()                
                .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
                .withNullValues(Boolean.TRUE)               
                .withDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

        return JsonbBuilder.create(config).toJson(this);

    }
    
}
