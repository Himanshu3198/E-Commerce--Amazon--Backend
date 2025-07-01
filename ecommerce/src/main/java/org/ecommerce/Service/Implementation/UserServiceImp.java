package org.ecommerce.Service.Implementation;

import org.ecommerce.Entity.User;
import org.ecommerce.Exception.UserNotFoundException;
import org.ecommerce.Repository.UserRepository;
import org.ecommerce.Service.Interface.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        try {
            userRepository.save(user);
            LOGGER.info("User successfully saved: {}", user.getEmail());
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while saving user: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unable to save user at this time. Try again later.", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        try {
            LOGGER.info("Fetching user by ID: {}", id);
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while retrieving user: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unable to retrieve user at this time. Try again later.", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        try {
            LOGGER.info("Fetching all users");
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new UserNotFoundException("No users found in the system.");
            }
            return users;
        } catch (DataAccessException ex) {
            LOGGER.error("Database error while retrieving all users: {}", ex.getMessage(), ex);
            throw new RuntimeException("Unable to fetch users at this time. Try again later.", ex);
        }
    }
}
