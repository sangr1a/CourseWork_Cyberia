package com.example.cyberia.controller;

import com.example.cyberia.models.User;
import com.example.cyberia.models.enums.Role;
import com.example.cyberia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    /**
     * AdminController отвечает за методы,
     * доступные только пользователям с
     * ролью администратора
     */

    @GetMapping("/panel")
    public String admin(Model model, Principal principal) {
        model.addAttribute("users", userService.list());
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "profile/admin/admin";
    }

    @PostMapping("/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin/panel";
    }

    @GetMapping("/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("roles", Role.values());
        return "profile/admin/user-edit";
    }

    @PostMapping("/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> tour) {
        userService.changeUserRoles(user, tour);
        return "redirect:/admin/panel/";
    }
}
