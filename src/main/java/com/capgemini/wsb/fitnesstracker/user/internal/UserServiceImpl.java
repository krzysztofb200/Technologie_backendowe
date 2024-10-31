package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsersByAgeOlderThan(final LocalDate time) {
        return userRepository.findAllByAgeOlderThan(time);
    }

    @Override
    public List<User> getAllUsersByEmailPart(final String emailPart) {
        return userRepository.findAllByEmailPart(emailPart);
    }

    @Override
    public Optional<User> updateUser(final Long userId, final User updatedUser) {
        if (updatedUser.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.updateUser(userId, updatedUser);
    }

    @Override
    public Optional<User> getUserDetails(final Long id, final String fullName, final LocalDate birthdate, final String email ) {
        int paramCount = 0;
        if (id != null) paramCount++;
        if (fullName != null) paramCount++;
        if (birthdate != null) paramCount++;
        if (email != null) paramCount++;
        if (paramCount > 1) throw new IllegalArgumentException("Only one parameter may be supplied");

        return userRepository.findByDetails(id, fullName, birthdate, email);
    }
}