package com.example.cyberia.models;

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
    private String title;
    private String description;
    private String game;
    private String city;
    private String address;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;
    private Integer availablePasses;
    private Boolean privacy;
    @ManyToMany(mappedBy = "attendedTours", fetch = FetchType.LAZY)
    private Set<User> attendees = new HashSet<>();

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
        this.setAvailablePasses(updatedTour.getAvailablePasses());
    }

}
