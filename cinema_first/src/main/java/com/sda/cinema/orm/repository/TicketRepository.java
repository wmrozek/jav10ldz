package com.sda.cinema.orm.repository;

import com.sda.cinema.orm.model.Screening;
import com.sda.cinema.orm.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT count(t) FROM Ticket t WHERE t.screening = :screening")
    int getTicketCountByScreening(@Param("screening") Screening screening);
}