package ru.mipt.smartslame.pdris.hw2.services;

import ru.mipt.smartslame.pdris.hw2.entity.AuditStamp;
import ru.mipt.smartslame.pdris.hw2.entity.User;
import ru.mipt.smartslame.pdris.hw2.enums.AuthResult;
import ru.mipt.smartslame.pdris.hw2.enums.AuthResultReason;
import ru.mipt.smartslame.pdris.hw2.exceptions.UserNotFoundException;

public class SignInService {
    private final UserService userService;
    private final AuditService auditService;

    public SignInService(UserService userService, AuditService auditService) {
        this.userService = userService;
        this.auditService = auditService;
    }

    public boolean trySignIn(String userName, String userPass) throws UserNotFoundException {
        if (!userService.hasUser(userName)) {
            auditService.addRecord(userName, new AuditStamp(
                    AuthResult.FAILURE,
                    AuthResultReason.USER_DOES_NOT_EXIST
            ));
            throw new UserNotFoundException(String.format("User %s doesn't exist.", userName));
        }

        User dbUser = userService.getUser(userName);

        if (!userPass.equals(dbUser.getPassword())) {
            auditService.addRecord(userName, new AuditStamp(
                    AuthResult.FAILURE,
                    AuthResultReason.WRONG_PASSWORD
            ));
            return false;
        }

        auditService.addRecord(userName, new AuditStamp(
                AuthResult.SUCCESS,
                AuthResultReason.OK
        ));
        return true;
    }

}
