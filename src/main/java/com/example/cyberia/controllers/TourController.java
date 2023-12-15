package com.example.cyberia.controllers;

import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class TourController {
    private final TourService tourService;

    @GetMapping("/")
    public String tours(@RequestParam(name = "searchWord", required = false) String title,
                        @RequestParam(name = "game", required = false) String game,
                        @RequestParam(name = "searchCity", required = false) String city,
                        Principal principal, Model model) {
        if ((game != null && !game.isEmpty()) || (city != null && !city.isEmpty())) {
            model.addAttribute("tours", tourService.listToursByGameAndCity(game, city));
        } else {
            model.addAttribute("tours", tourService.listToursByTitle(title));
        }

        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        model.addAttribute("selectedGame", game);
        model.addAttribute("selectedCity", city);
        return "tour/tours";
    }

    @GetMapping("/tour/{id}")
    public String tourInfo(@PathVariable Long id, Model model, Principal principal) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("tour", tour);
        model.addAttribute("authorTour", tour.getUser());
        return "tour/tour-info";
    }

    @PreAuthorize("hasAuthority('ROLE_VIEWER')")
    @PostMapping("/tour/add-to-favorites/{id}")
    public String addToFavorites(@PathVariable Long id, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);
        tourService.addToFavorites(id, user);
        return "redirect:/tour/" + id; // Redirect to the tour details page
    }

    @PreAuthorize("hasAuthority('ROLE_VIEWER')")
    @PostMapping("/tour/remove-from-favorites/{id}")
    public String removeFromFavorites(@PathVariable Long id, Principal principal) {
        User user = tourService.getUserByPrincipal(principal);
        tourService.removeFromFavorites(id, user);
        return "redirect:/tour/" + id; // Redirect to the tour details page
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