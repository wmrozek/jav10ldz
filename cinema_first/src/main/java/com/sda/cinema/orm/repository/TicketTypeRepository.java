package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {

}
