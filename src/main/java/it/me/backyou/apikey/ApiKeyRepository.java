package it.me.backyou.apikey;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with api keys table
 */
public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
}