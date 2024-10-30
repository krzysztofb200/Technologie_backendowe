package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping(value = "/simple")
    public List<UserNameIdDto> getNameIdAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toNameIdDto)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public Optional<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.getUser(id).map(userMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));
    }

    @DeleteMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable("userId") Long userId) {
        userService.removeUser(userId);
    }

    @GetMapping(value = "/email")
    public List<UserEmailDto> getAllUsersByEmailPart(@RequestParam("email") String email) {
        return userService.getAllUsersByEmailPart(email).stream().map(userMapper::toEmailDto).toList();
    }

    @GetMapping(value = "/older/{time}")
    public List<UserDto> getAllUsersByAgeOlderThan(@PathVariable("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userService.getAllUsersByAgeOlderThan(time).stream().map(userMapper::toDto).toList();
    }

    @PutMapping(value = "/{userId}")
    public Optional<UserDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto updatedUserDto){
        return userService.updateUser(userId, userMapper.toEntity(updatedUserDto)).map(userMapper::toDto);
    }

    @GetMapping(value = "/details")
    public Optional<User> getUserDetails(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthdate,
            @RequestParam(required = false) String email) {
        return userService.getUserDetails(id, fullName, birthdate, email);
    }
}