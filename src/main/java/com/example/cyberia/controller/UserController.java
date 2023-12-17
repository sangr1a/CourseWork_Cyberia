package com.example.cyberia.controller;

import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.services.TourService;
import com.example.cyberia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ORG', 'ROLE_VIEWER')")
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final TourService tourService;

    /**
     * UserController отвечает за методы,
     * доступные только авторизированным пользователям
     */

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile/user-profile";
    }

    @GetMapping("/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("tours", user.getTours());
        return "profile/user-info";
    }

    @GetMapping("/edit-profile")
    public String editProfileForm(Model model, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "redirect:/user/profile";
    }

    @PostMapping("/edit-profile")
    public String editProfile(@ModelAttribute("user") User updatedUser, Principal principal) {
        userService.updateUserProfile(principal, updatedUser);
        return "redirect:/user/profile";
    }

    @GetMapping("/my/favorites")
    public String viewFavoriteTours(Model model, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);

        Set<Tour> favoriteTours = user.getFavoriteTours(); // Change this based on your actual logic

        model.addAttribute("user", user);
        model.addAttribute("favoriteTours", favoriteTours);

        return "profile/user/favorites";
    }

    @GetMapping("/my/attended-tours")
    public String viewAttendedTours(Model model, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);

        Set<Tour> attendedTours = user.getAttendedTours(); // Change this based on your actual logic

        model.addAttribute("user", user);
        model.addAttribute("attendedTours", attendedTours);

        return "profile/user/attended-tours";
    }

}
