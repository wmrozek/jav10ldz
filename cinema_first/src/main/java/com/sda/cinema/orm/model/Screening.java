package com.sda.cinema.orm.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    private int id;
    @Column(name = "id_cinema_room")
    private int idCinemaRoom;
    @Column(name = "id_movie")
    private int idMovie;
    @Column(name = "id_movie_technology")
    private int idMovieTechnology;
    @Column(name = "id_language_option")
    private int idLanguageOption;
    @Column(name = "price")
    private int price;
    @Column(name = "screening_date")
    private LocalDate screeningDate;
    @Column(name = "screening_time")
    private LocalTime screeningTime;

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getIdCinemaRoom() {
        return idCinemaRoom;
    }

    public void setIdCinemaRoom(int idCinemaRoom) {
        this.idCinemaRoom = idCinemaRoom;
    }

    public int getIdMovieTechnology() {
        return idMovieTechnology;
    }

    public void setIdMovieTechnology(int idMovieTechnology) {
        this.idMovieTechnology = idMovieTechnology;
    }

    public int getIdLanguageOption() {
        return idLanguageOption;
    }

    public void setIdLanguageOption(int idLanguageOption) {
        this.idLanguageOption = idLanguageOption;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate screeningDate) {
        this.screeningDate = screeningDate;
    }

    public LocalTime getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(LocalTime screeningTime) {

        this.screeningTime = screeningTime;
    }
    //</editor-fold>
}