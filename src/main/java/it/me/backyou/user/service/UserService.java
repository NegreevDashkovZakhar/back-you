package it.me.backyou.user.service;

import it.me.backyou.user.User;
import it.me.backyou.apikey.ApiKey;
import it.me.backyou.apikey.ApiKeyRepository;
import it.me.backyou.user.exception.UserNotFoundException;
import it.me.backyou.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service working with users logic such as registering users, api keys management etc.
 */
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ApiKeyRepository apiKeyRepository;

    /**
     * Default service constructor initializing needed repositories
     *
     * @param userRepository   user repository for working with users table
     * @param apiKeyRepository api key repository for working with api keys table
     */
    @Autowired
    public UserService(final UserRepository userRepository, final ApiKeyRepository apiKeyRepository) {
        this.userRepository = userRepository;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public long registerUser(final String email, final String password) {
        User user = new User(email, password);
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public Set<ApiKey> getUserApiKeys(final long userId) {
        try {
            User user = userRepository.getById(userId);
            return user.getApiKeys();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public String addNewApiKey(final long userId) {
        try {
            ApiKey apiKey = new ApiKey();
            User user = userRepository.getById(userId);
            apiKey.setUser(user);
            apiKeyRepository.save(apiKey);
            return apiKey.getApiKey();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public long getUserId(final String email, final String password) {
        try {
            return userRepository.getUserId(email, password);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}
