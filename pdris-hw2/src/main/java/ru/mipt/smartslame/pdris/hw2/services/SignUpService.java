package ru.mipt.smartslame.pdris.hw2.services;

import ru.mipt.smartslame.pdris.hw2.exceptions.UserCreationException;

public class SignUpService {
    private final UserService userService;
    private final AuditService auditService;

    public SignUpService(UserService userService, AuditService auditService) {
        this.userService = userService;
        this.auditService = auditService;
    }

    private void validateParams(String userName, String userPass) throws UserCreationException {
        if (userName.equals("admin")) {
            throw new UserCreationException(String.format("You can not create %s user.", userName));
        }

        if (userName.isEmpty()) {
            throw new UserCreationException("Name must not be empty.");
        }

        if (userPass.isEmpty()) {
            throw new UserCreationException("Password must not be empty.");
        }
    }

    public boolean trySignUp(String userName, String userPass) throws UserCreationException {
        validateParams(userName, userPass);

        if (userService.hasUser(userName)) {
            return false;
        }

        userService.addUser(userName, userPass);

        return true;
    }
}
