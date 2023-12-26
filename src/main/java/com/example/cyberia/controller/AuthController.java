package com.example.cyberia.controller;

import com.example.cyberia.models.User;
import com.example.cyberia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {
    private final UserService userService;

    /**
     * AuthController отвечает за методы,
     * необходимые для входа в учетную запись
     * или ее создания
     */

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "signlog/login";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "signlog/registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signlog/registration";
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "signlog/registration";
        }
        return "redirect:/auth/login";
    }

}
