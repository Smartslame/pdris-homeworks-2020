package ru.mipt.smartslame.pdris.hw2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mipt.smartslame.pdris.hw2.exceptions.UserNotFoundException;
import ru.mipt.smartslame.pdris.hw2.services.SignInService;

@Controller
public class SignInController {
    private final SignInService signInService;

    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @GetMapping({"/", "sign_in"})
    public String index() {
        return "sign_in.html";
    }

    @PostMapping("/sign_in")
    public String signIn(@RequestParam String userName, @RequestParam String userPass, Model model) {
        boolean sighInSucceeded;

        try {
            sighInSucceeded = signInService.trySignIn(userName, userPass);
        } catch (UserNotFoundException e) {
            model.addAttribute("msg", e.getMessage());
            return "sign_in.html";
        }

        if (!sighInSucceeded) {
            model.addAttribute("msg", String.format("Password is incorrect.", userName));
            return "sign_in.html";
        }

        model.addAttribute("name", userName);
        return "welcome.html";
    }
}
