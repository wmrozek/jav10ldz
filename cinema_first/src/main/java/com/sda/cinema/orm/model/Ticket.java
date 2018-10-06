package com.sda.cinema.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    private int id;
    @Column(name = "sale_date")
    private LocalDate saleDate;
    @Column(name = "id_screening")
    private int idScreening;
    @Column(name = "id_ticket_type")
    private int idTicketType;

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

    public int getIdScreening() {
        return idScreening;
    }

    public void setIdScreening(int idScreening) {
        this.idScreening = idScreening;
    }

    public int getIdTicketType() {
        return idTicketType;
    }

    public void setIdTicketType(int idTicketType) {
        this.idTicketType = idTicketType;
    }
}