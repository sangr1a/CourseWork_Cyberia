package com.example.cyberia.models;

import com.example.cyberia.models.enums.TourStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean privacy;
    private String title;
    private String format;
    private String formatType;
    private Integer numberOfPlayers;
    private Boolean isLan;
    private String city;
    private String address;
    private String prize;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToMany(mappedBy = "attendedTours", fetch = FetchType.LAZY)
    private Set<User> attendees = new HashSet<>();

    @ElementCollection(targetClass = TourStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tour_status", joinColumns = @JoinColumn(name = "tour_id"))
    @Enumerated(EnumType.STRING)
    private Set<TourStatus> tourStatus = new HashSet<>();

    private String description;
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void onCreate() {
        dateOfCreated = LocalDateTime.now();
    }

    public void updateTourDetails(Tour updatedTour) {
        this.setTitle(updatedTour.getTitle());
        this.setDescription(updatedTour.getDescription());
        this.setGame(updatedTour.getGame());;
        this.setCity(updatedTour.getCity());
        this.setAddress(updatedTour.getAddress());
        this.setNumberOfPlayers(updatedTour.getNumberOfPlayers());
    }

}
