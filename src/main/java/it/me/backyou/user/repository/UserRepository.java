package it.me.backyou.user.repository;

import it.me.backyou.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository for working with users table
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Method checking whether user with such email exists
     *
     * @param email email of the user to be checked
     * @return 1 if user with such email is registered, 0 otherwise
     */
    @Query(nativeQuery = true,
            value = "SELECT COUNT (1) FROM users WHERE email = ?1 ;")
    int userExist(String email);

    /**
     * Method for getting users id from email and password
     *
     * @param email    email of the user
     * @param password password of the user
     * @return users id if found user with such email and password, null otherwise
     */
    @Query(nativeQuery = true,
            value = "SELECT user_id FROM users WHERE email = ?1 AND password = ?2 ;")
    Long getUserId(String email, String password);
}