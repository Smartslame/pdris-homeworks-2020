package ru.mipt.smartslame.pdris.hw2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mipt.smartslame.pdris.hw2.services.AuditService;
import ru.mipt.smartslame.pdris.hw2.services.SignInService;
import ru.mipt.smartslame.pdris.hw2.services.SignUpService;
import ru.mipt.smartslame.pdris.hw2.services.UserService;

@Configuration
public class Config {
    @Bean
    public AuditService auditService() {
        return new AuditService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public SignInService signInService(UserService userService, AuditService auditService) {
        return new SignInService(userService, auditService);
    }

    @Bean
    SignUpService signUpService(UserService userService, AuditService auditService) {
        return new SignUpService(userService, auditService);
    }
}
