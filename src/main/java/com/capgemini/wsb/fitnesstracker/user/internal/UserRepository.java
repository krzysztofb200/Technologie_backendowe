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

    default List<User> findAllByAgeOlderThan(LocalDate time) {
        return findAll().stream().filter(user -> user.getBirthdate().isBefore(time)).toList();
    }

    default List<User> findAllByEmailPart(String emailPart) {
        return findAll().stream().filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase())).toList();
    }

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

    default Optional<User> findByDetails(Long Id, String fullName, LocalDate birthdate, String email) {
        return findAll().stream().filter(user ->
                Objects.equals(user.getId(), Id) ||
                        Objects.equals(user.getFirstName() + " " + user.getLastName(), fullName) ||
                        Objects.equals(user.getBirthdate(), birthdate) || Objects.equals(user.getEmail(), email))
                .findFirst();
    }
}
