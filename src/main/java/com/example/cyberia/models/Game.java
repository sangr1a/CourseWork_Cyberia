package com.example.cyberia.models;

import com.example.cyberia.models.enums.AgeRating;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private String publishYear;
    private String description;
    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "game")
    private List<Tour> tours = new ArrayList<>();
}
