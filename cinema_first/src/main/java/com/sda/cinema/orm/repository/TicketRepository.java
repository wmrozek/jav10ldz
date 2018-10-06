package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}