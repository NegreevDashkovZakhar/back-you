package it.me.backyou.user;

import it.me.backyou.user.apikey.ApiKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Entity class
 * Represents connection between users and their apikey
 * Has id and apiKey fields
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<ApiKey> apiKeys;

    /**
     * Default constructor. does not set any fields
     */
    public User() {
    }

    /**
     * Constructor initializing email and password fields
     *
     * @param email    given user email
     * @param password given user password
     */
    public User(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public Set<ApiKey> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(Set<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}