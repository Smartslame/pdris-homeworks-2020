package ru.mipt.smartslame.pdris.hw2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mipt.smartslame.pdris.hw2.exceptions.UserCreationException;
import ru.mipt.smartslame.pdris.hw2.services.SignUpService;

@Controller
public class SignUpController {
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/sign_up")
    public String signUpPage() {
        return "sign_up.html";
    }

    @PostMapping("/sign_up")
    public String signUp(@RequestParam String userName, @RequestParam String userPass, Model model) {
        boolean signUpSucceeded;

        try {
            signUpSucceeded = signUpService.trySignUp(userName, userPass);
        } catch (UserCreationException e) {
            model.addAttribute("msg", e.getMessage());
            return "sign_up.html";
        }

        if (!signUpSucceeded) {
            model.addAttribute("msg", String.format("User %s already exists.", userName));
            return "sign_up.html";
        }

        model.addAttribute("msg", String.format("User %s created.", userName));

        return "sign_in.html";
    }
}
