package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {
    /**
     * creates {@link User}.
     *
     * @return An {@link User} that has been created,
     */
    User createUser(User user);

    /**
     * removes {@link User}.
     */
    void removeUser(Long userId);

    /**
     * updates {@link User}.
     *
     * @return An {@link Optional} {@link User} that has been updated, or {@link Optional#empty()} if not found
     */
    Optional<User> updateUser(Long userId, User updatedUser);

}
