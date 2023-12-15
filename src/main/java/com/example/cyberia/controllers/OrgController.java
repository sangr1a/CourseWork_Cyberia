package com.example.cyberia.controllers;

import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ORG')")
public class OrgController {
    private final TourService tourService;

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

    @GetMapping("/tour/edit-tour/{id}")
    public String editTourForm(@PathVariable Long id, Model model, Principal principal) {
        Tour tour = tourService.getTourById(id);
        if (tour == null) {
            return "redirect:/my/tours";
        }
        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("tour", tour);
        return "profile/org/edit-tour";
    }

    @PostMapping("/tour/edit-tour/{id}")
    public String editTour(@ModelAttribute("tour") Tour tour, Principal principal) throws IOException {
        tourService.saveTour(principal, tour);
        return "redirect:/my/tours";
    }

    @GetMapping("/my/tours")
    public String userTours(Principal principal, Model model) {
        User user = tourService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("tours", user.getTours());
        return "profile/org/my-tours";
    }

}
