package it.me.backyou.user.apikey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Entity class
 * Representing api keys and all data connected with them
 */
@Entity
@Table(name = "api_key")
public class ApiKey {
    @Id
    private String apiKey = UUID.randomUUID().toString();

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}