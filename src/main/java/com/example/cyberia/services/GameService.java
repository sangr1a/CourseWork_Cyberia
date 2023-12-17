package com.example.cyberia.services;

import com.example.cyberia.models.Game;
import com.example.cyberia.models.Tour;
import com.example.cyberia.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public List<Game> listGamesByName(String name) {
        if (name != null && !name.isEmpty()) {
            return gameRepository.findByName(name);
        } else {
            return gameRepository.findAll();
        }
    }
}
