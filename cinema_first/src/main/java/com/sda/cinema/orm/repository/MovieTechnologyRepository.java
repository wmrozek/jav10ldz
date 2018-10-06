package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.MovieTechnology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieTechnologyRepository extends JpaRepository<MovieTechnology, Integer> {
}