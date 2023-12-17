package com.example.cyberia.repositories;

import com.example.cyberia.models.Game;
import com.example.cyberia.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByTitle(String title);

    @Modifying
    @Query("delete from Tour f where f.id = ?1")
    void delete(Long id);

    List<Tour> findByGameAndCityIgnoreCase(Game game, String city);
    List<Tour> findByGame(Game game);
    List<Tour> findByCityIgnoreCase(String city);

    @Query("SELECT f FROM Tour f WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Tour> findByTitleContainingIgnoreCase(@Param("title") String title);
}