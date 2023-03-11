package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.UserController;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.UserOld;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceOld {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Map<String, UserOld> users = new HashMap<>();

    public Collection<UserOld> findAllUsers() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users.values();
    }

    public UserOld createUser(UserOld user) {
        checkEmail(user);
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException(String.format(
                    "Пользователь с электронной почтой %s уже зарегистрирован.",
                    user.getEmail()
            ));
        }
        log.debug("Создается пользователь с email: {}", user.getEmail());
        users.put(user.getEmail(), user);
        return user;
    }

    public UserOld updateUser(UserOld user) {
        checkEmail(user);
        users.put(user.getEmail(), user);
        return user;
    }

    public UserOld findUserByEmail(String email) {
        if (email == null) {
            return null;
        }
        return users.get(email);
    }

    private void checkEmail(UserOld user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
        }
    }
}
