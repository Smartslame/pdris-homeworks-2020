package ru.mipt.smartslame.pdris.hw2.services;

import ru.mipt.smartslame.pdris.hw2.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void addUser(String userName, String userPass) {
        users.put(userName, new User(userName, userPass));
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean hasUser(String username) {
        return users.containsKey(username);
    }
}
