package com.example.cyberia.repositories;

import com.example.cyberia.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByName(String name);
}
