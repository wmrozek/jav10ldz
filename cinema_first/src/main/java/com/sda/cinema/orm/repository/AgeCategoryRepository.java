package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.AgeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeCategoryRepository extends JpaRepository<AgeCategory, Integer> {
}