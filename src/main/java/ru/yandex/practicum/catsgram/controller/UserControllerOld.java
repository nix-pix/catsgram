package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.UserOld;
import ru.yandex.practicum.catsgram.service.UserServiceOld;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserControllerOld {
    private final UserServiceOld userService;

    public UserControllerOld(UserServiceOld userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<UserOld> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public UserOld createUser(@RequestBody UserOld user) {
        return userService.createUser(user);
    }

    @PutMapping
    public UserOld updateUser(@RequestBody UserOld user) {
        return userService.updateUser(user);
    }

    @GetMapping("/user/{userMail}")
    public UserOld findUserByEmail(@PathVariable("userMail") String userMail) {
        return userService.findUserByEmail(userMail);
    }
}
