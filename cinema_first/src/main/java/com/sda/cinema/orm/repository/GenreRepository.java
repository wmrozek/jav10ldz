package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}