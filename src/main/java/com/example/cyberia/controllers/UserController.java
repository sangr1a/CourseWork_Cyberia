package com.example.cyberia.controllers;

import com.example.cyberia.models.User;
import com.example.cyberia.services.TourService;
import com.example.cyberia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TourService tourService;

    //Профиль пользователя
    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile/profile";
    }

    //Информация о пользователе
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("tours", user.getTours());
        return "profile/user-info";
    }

    //Участие пользователем в турнире
    @PostMapping("/tour/attend/{id}")
    public String attendTour(@PathVariable Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        try {
            tourService.attendTour(id, user);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

    //Отмена участия пользователем в турнире
    @PostMapping("/tour/cancel-attendance/{id}")
    public String cancelAttendance(@PathVariable Long id, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        try {
            tourService.cancelAttendance(id, user);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }



}
