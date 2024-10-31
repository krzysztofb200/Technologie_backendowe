package com.capgemini.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return A {@link List} containing the all found users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves all users based on their birthdate.
     *
     * @param time The birthdate before which all users have been born
     * @return A {@link List} containing all found users,
     */
    List<User> getAllUsersByAgeOlderThan(LocalDate time);

    /**
     * Retrieves all users based on their email.
     *
     * @param emailPart The part of an email of users to be searched
     * @return A {@link List} containing all found users,
     */
    List<User> getAllUsersByEmailPart(String emailPart);

    /**
     * Retrieves a user based on their id or full name or birthdate or email.
     *
     * @param id The id of user to be searched
     * @param fullName The full name of the user to be searched
     * @param birthdate The birthdate of the user to be searched
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserDetails(Long id, String fullName, LocalDate birthdate, String email );
}
