package com.example.cyberia.services;

import com.example.cyberia.models.Game;
import com.example.cyberia.models.Tour;
import com.example.cyberia.models.User;
import com.example.cyberia.models.enums.TourStatus;
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
    private final GameService gameService;

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public List<Tour> listToursByGame(Long gameId) {
        if (gameId != null) {
            Game game = gameService.getGameById(gameId);
            if (game != null) {
                return tourRepository.findByGameId(gameId);
            } else {
                log.error("Game with id = {} is not found", gameId);
                return tourRepository.findAll();
            }
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
    public void saveTour(Principal principal, Tour tour, Long gameId) throws IOException {
        User user = getUserByPrincipal(principal);
        Game game = gameService.getGameById(gameId); // Assuming you have a GameService with a method to get a game by ID

        if (user != null && game != null) {
//            tour.setUser(user);
//            tour.setGame(game);
//            tour.setNumberOfPlayers(tour.getNumberOfPlayers());

            tour.getTourStatus().add(TourStatus.NEW);

            log.info("Saving new Tour. Title: {}; Author email: {}", tour.getTitle(), user.getEmail());
            tourRepository.save(tour);
        } else {
            log.error("Tour with id = {} is not found", tour.getId());
        }
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
    public void attendTour(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            addAttendeeToTour(tour, user);
            log.info("User {} attended tour {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to attend tour. Tour or user not found.");
        }
    }

    @Transactional
    public void cancelAttendance(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null) {
            removeAttendeeFromTour(tour, user);
            log.info("User {} canceled attendance for tour {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to cancel attendance. Tour or user not found.");
        }
    }

    @Transactional
    public void addAttendeeToTour(Tour tour, User user) {
        tour.getAttendees().add(user);
        user.getAttendedTours().add(tour);
        tourRepository.save(tour);
        userRepository.save(user);
    }

    @Transactional
    public void removeAttendeeFromTour(Tour tour, User user) {
        if (tour.getAttendees().contains(user)) {
            tour.getAttendees().remove(user);
            user.getAttendedTours().remove(tour);
            tourRepository.save(tour);
            userRepository.save(user);
        } else {
            log.error("User {} is not a participant in the tournament {}.", user.getEmail(), tour.getTitle());
            throw new RuntimeException("User is not a participant in the tournament.");
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
    public void removeParticipant(Long tourId, User user) {
        Tour tour = getTourById(tourId);
        if (tour != null && user != null && tour.getAttendees().contains(user)) {
            tour.getAttendees().remove(user);
            user.getAttendedTours().remove(tour);
            tourRepository.save(tour);
            userRepository.save(user);
            log.info("Participant {} removed from tournament {}.", user.getEmail(), tour.getTitle());
        } else {
            log.error("Unable to remove participant. Tour, user, or not a participant.");
        }
    }

}
