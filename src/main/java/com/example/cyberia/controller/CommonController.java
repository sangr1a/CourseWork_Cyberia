package com.example.cyberia.controller;

import com.example.cyberia.models.Game;
import com.example.cyberia.models.Tour;
import com.example.cyberia.services.GameService;
import com.example.cyberia.services.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final TourService tourService;
    private final GameService gameService;

    /**
     * CommonController отвечает за методы,
     * доступные пользователям с и без
     * авторизации
     */

    @GetMapping("/")
    public String tours(@RequestParam(name = "searchWord", required = false) String title,
                        @RequestParam(name = "selectedGameID", required = false) Long gameId,
                        @RequestParam(name = "gameName", required = false) String gameName,
                        Principal principal, Model model) {
        if (gameId != null) {
            model.addAttribute("tours", tourService.listToursByGame(gameId));
        } else {
            model.addAttribute("tours", tourService.listToursByTitle(title));
        }

        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("games", gameService.listGamesByName(gameName));
        model.addAttribute("searchWord", title);
        model.addAttribute("selectedGameID", gameId);
        return "tour/main";
    }

    @GetMapping("/tour/{id}")
    public String tourInfo(@PathVariable Long id, Model model, Principal principal) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("user", tourService.getUserByPrincipal(principal));
        model.addAttribute("tour", tour);
        model.addAttribute("authorTour", tour.getUser());
        model.addAttribute("tourGame", tour.getGame());
        return "tour/tour-info";
    }

}
