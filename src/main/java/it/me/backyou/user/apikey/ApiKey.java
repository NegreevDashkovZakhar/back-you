package it.me.backyou.user.apikey;

import it.me.backyou.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_key_id", nullable = false)
    private long apiKeyId;

    @Column(unique = true)
    private String apiKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Default constructor initializing api key from generated random UUID
     */
    public ApiKey() {
        this.apiKey = UUID.randomUUID().toString();
    }

    /**
     * Constructor initializing api key from existing string as id
     *
     * @param apiKey given api key string
     */
    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getApiKeyId() {
        return apiKeyId;
    }

    public void setApiKeyId(long apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}