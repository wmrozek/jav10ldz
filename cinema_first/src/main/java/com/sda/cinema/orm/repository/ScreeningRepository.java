package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.CinemaRoom;
import com.sda.cinema.orm.model.Movie;
import com.sda.cinema.orm.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findByMovie(Movie movie);
    List<Screening> findByScreeningDateAndCinemaRoom(LocalDate screeningDate, CinemaRoom cinemaRoom);
}
