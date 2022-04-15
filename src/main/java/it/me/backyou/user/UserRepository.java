package it.me.backyou.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for working with users table
 */
public interface UserRepository extends JpaRepository<User, Long> {
}