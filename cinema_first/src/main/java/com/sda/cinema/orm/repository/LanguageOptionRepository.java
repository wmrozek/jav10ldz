package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.LanguageOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageOptionRepository extends JpaRepository<LanguageOption, Integer> {
}
