package com.example.cyberia.models;

import com.example.cyberia.models.enums.TourStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;
    @Setter
    private String title;
    @Setter
    private String format;
    @Setter
    private String formatType;
    @Setter
    private Integer numberOfPlayers;
    @Setter
    private Boolean isLan;
    @Setter
    private String city;
    @Setter
    private String address;
    @Setter
    private String prize;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Setter
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Setter
    @ManyToMany(mappedBy = "attendedTours", fetch = FetchType.LAZY)
    private Set<User> attendees = new HashSet<>();

    @Setter
    @ElementCollection(targetClass = TourStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tour_status", joinColumns = @JoinColumn(name = "tour_id"))
    @Enumerated(EnumType.STRING)
    private Set<TourStatus> tourStatus = new HashSet<>();

    @Setter
    private String description;
    @Setter
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void onCreate() {
        dateOfCreated = LocalDateTime.now();
    }

    public void updateTourDetails(Tour updatedTour) {
        this.setTitle(updatedTour.getTitle());
        this.setDescription(updatedTour.getDescription());
        this.setGame(updatedTour.getGame());
        this.setCity(updatedTour.getCity());
        this.setAddress(updatedTour.getAddress());
        this.setNumberOfPlayers(updatedTour.getNumberOfPlayers());
    }
}
