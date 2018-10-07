package com.sda.cinema.orm.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    private int id;
    @Column(name = "sale_date")
    private LocalDate saleDate;
    @OneToOne
    @JoinColumn(name = "id_screening")
    private Screening screening;
    @OneToOne
    @JoinColumn(name = "id_ticket_type")
    private TicketType ticketType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}