package com.example.cyberia.services;

import com.example.cyberia.models.Game;
import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.repositories.GameRepository;
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

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public List<Tour> listTours(String title) {
        if (title != null) return tourRepository.findByTitle(title);
        return tourRepository.findAll();
    }
    public List<Tour> listToursByGameAndCity(Game game, String city) {
        if (game != null && city != null && !city.isEmpty()) {
            return tourRepository.findByGameAndCityIgnoreCase(game, city);
        } else if (game != null) {
            return tourRepository.findByGame(game);
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
        tour.setNumberOfPlayers(tour.getNumberOfPlayers());
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

    @Transactional
    public void updateTourDetails(Long tourId, Tour updatedTour) {
        Tour existingTour = getTourById(tourId);
        if (existingTour != null) {
            existingTour.updateTourDetails(updatedTour);
            tourRepository.save(existingTour);
            log.info("Tour details updated. Tour ID: {}", tourId);
        } else {
            log.error("Tour with ID {} not found.", tourId);
        }
    }

    @Transactional
    public void attendTour(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            tour.getAttendees().add(user);
            user.getAttendedTours().add(tour);
            tourRepository.save(tour);
            userRepository.save(user);
            log.info("User {} attended tour {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to attend tour. Tour or user not found.");
        }
    }

    @Transactional
    public void cancelAttendance(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            tour.getAttendees().remove(user);
            user.getAttendedTours().remove(tour);
            tourRepository.save(tour);
            userRepository.save(user);
            log.info("User {} canceled attendance for tour {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to cancel attendance. Tour or user not found.");
        }
    }

    @Transactional
    public void addToFavorites(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            user.addFavoriteTour(tour);
            userRepository.save(user);
            log.info("User {} added tour {} to favorites.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to add tour to favorites. Tour or user not found.");
        }
    }

    @Transactional
    public void removeFromFavorites(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            user.removeFavoriteTour(tour);
            userRepository.save(user);
            log.info("User {} removed tour {} from favorites.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to remove tour from favorites. Tour or user not found.");
        }
    }

    @Transactional
    public void registerForTour(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            tour.getAttendees().add(user);
            user.getAttendedTours().add(tour);
            tourRepository.save(tour);
            userRepository.save(user);
            log.info("User {} registered for tournament {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to register for the tournament. Tour or user not found.");
            throw new RuntimeException("Unable to register for the tournament.");
        }
    }

    @Transactional
    public void removeParticipant(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            if (tour.getAttendees().contains(user)) {
                tour.getAttendees().remove(user);
                user.getAttendedTours().remove(tour);
                tourRepository.save(tour);
                userRepository.save(user);
                log.info("Participant {} removed from tournament {}.", user.getEmail(), tour.getTitle());
            } else {
                log.error("User {} is not a participant in the tournament {}.", user.getEmail(), tour.getTitle());
                throw new RuntimeException("User is not a participant in the tournament.");
            }
        } else {
            log.error("Unable to remove participant. Tour or user not found.");
            throw new RuntimeException("Unable to remove participant.");
        }
    }

}
