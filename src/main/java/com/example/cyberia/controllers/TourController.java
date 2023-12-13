package com.example.cyberia.controllers;

import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

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
        return "tours";
    }

    @GetMapping("/tour/{id}")
    public String tourInfo(@PathVariable Long id, Model model, Principal principal) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("tour", tour);
        model.addAttribute("authorTour", tour.getUser());
        return "tour-info";
    }

    @PostMapping("/tour/create")
    public String createTour(Tour tour, Principal principal) throws IOException {
        tourService.saveTour(principal, tour);
        return "redirect:/my/tours";
    }

    @PostMapping("/tour/delete/{id}")
    public String deleteTour(@PathVariable Long id, Principal principal) {
        try {
            tourService.deleteTour(tourService.getUserByPrincipal(principal), id);
            return "redirect:/my/tours";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/my/tours";
        }
    }

    @GetMapping("/my/tours")
    public String userTours(Principal principal, Model model) {
        User user = tourService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("tours", user.getTours());
        return "my-tours";
    }
}
