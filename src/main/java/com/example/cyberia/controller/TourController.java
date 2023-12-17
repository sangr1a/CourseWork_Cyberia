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
@RequestMapping("/tour")
@PreAuthorize("hasAuthority('ROLE_VIEWER')")
public class TourController {
    private final TourService tourService;
    private final UserService userService;

    /**
     * TourController отвечает за методы,
     * доступные авторизированным пользователям
     * для взаимодействия с турнирами
     */

    @PostMapping("/attend/{id}")
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

    @PostMapping("/cancel-attendance/{id}")
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

    @PostMapping("/add-to-favorites/{id}")
    public String addToFavorites(@PathVariable Long id, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);
        tourService.addToFavorites(id, user);
        return "redirect:/tour/" + id;
    }

    @PostMapping("/remove-from-favorites/{id}")
    public String removeFromFavorites(@PathVariable Long id, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);
        tourService.removeFromFavorites(id, user);
        return "redirect:/tour/" + id;
    }
}