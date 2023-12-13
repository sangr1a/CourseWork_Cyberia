package com.example.cyberia.services;

import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.repositories.TourRepository;
import com.example.cyberia.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    public List<Tour> listTours(String title) {
        if (title != null) return tourRepository.findByTitle(title);
        return tourRepository.findAll();
    }
    public List<Tour> listToursByGameAndCity(String game, String city) {
        if (game != null && !game.isEmpty() && city != null && !city.isEmpty()) {
            return tourRepository.findByGameIgnoreCaseAndCityIgnoreCase(game, city);
        } else if (game != null && !game.isEmpty()) {
            return tourRepository.findByGameIgnoreCase(game);
        } else if (city != null && !city.isEmpty()) {
            return tourRepository.findByCityIgnoreCase(city);
        } else {
            return tourRepository.findAll();
        }
    }

    public List<Tour> listToursByTitle(String title) {
        if (title != null && !title.isEmpty()) {
            return tourRepository.findByTitleContainingIgnoreCase(title);
        } else {
            return tourRepository.findAll();
        }
    }
    public void saveTour(Principal principal, Tour tour) throws IOException {
        tour.setUser(getUserByPrincipal(principal));
        log.info("Saving new Tour. Title: {}; Author email: {}", tour.getTitle(), tour.getUser().getEmail());
        tourRepository.save(tour);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    @Transactional
    public void deleteTour(User user, Long id) {
        Tour tour = tourRepository.findById(id)
                .orElse(null);
        if (tour != null) {
            if (tour.getUser().getId().equals(user.getId())) {
                tourRepository.delete(tour.getId());
                log.info("Tour with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this tour with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Tour with id = {} is not found", id);
        }
    }

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }
}
