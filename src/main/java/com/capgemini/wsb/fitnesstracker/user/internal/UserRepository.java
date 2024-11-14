package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Query searching users by age. It matches by older than given time.
     *
     * @param time {@link LocalDate} age of the user to search
     * @return {@link List} containing found users
     */
    default List<User> findAllByAgeOlderThan(LocalDate time) {
        return findAll().stream().filter(user -> user.getBirthdate().isBefore(time)).toList();
    }

    /**
     * Query searching users by email address. It matches by contains.
     *
     * @param emailPart part of email of the users to search
     * @return {@link List} containing found users
     */
    default List<User> findAllByEmailPart(String emailPart) {
        return findAll().stream().filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase())).toList();
    }

    /**
     * Query updating user chosen by id.
     *
     * @param Id Id of the user to update
     * @param updatedUser {@link User} updated user data
     * @return {@link Optional} containing updated user or {@link Optional#empty()} if none matched
     */
    default Optional<User> updateUser(long Id, User updatedUser) {
        return findAll().stream().filter(user -> Objects.equals(user.getId(), Id)).map(user -> {
            if(updatedUser.getEmail() != null){
                user.setEmail(updatedUser.getEmail());
            }
            if(updatedUser.getBirthdate() != null){
                user.setBirthdate(updatedUser.getBirthdate());
            }
            if(updatedUser.getFirstName() != null){
                user.setFirstName(updatedUser.getFirstName());
            }
            if(updatedUser.getLastName() != null){
                user.setLastName(updatedUser.getLastName());
            }
            return save(user);
        }).findFirst();
    }

    /**
     * Query searching users by chosen detail. It matches by exact match.
     *
     * @param Id The id of user to be searched
     * @param fullName The full name of the user to be searched
     * @param birthdate The birthdate of the user to be searched
     * @param email The email of the user to be searched
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByDetails(Long Id, String fullName, LocalDate birthdate, String email) {
        return findAll().stream().filter(user ->
                Objects.equals(user.getId(), Id) ||
                        Objects.equals(user.getFirstName() + " " + user.getLastName(), fullName) ||
                        Objects.equals(user.getBirthdate(), birthdate) || Objects.equals(user.getEmail(), email))
                .findFirst();
    }
}
