package com.sda.cinema.orm.model;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "id_cinema_room")
    private CinemaRoom cinemaRoom;
    @OneToOne
    @JoinColumn(name = "id_movie")
    private Movie movie;
    @OneToOne
    @JoinColumn(name = "id_language_option")
    private LanguageOption languageOption;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "screening_date")
    private LocalDate screeningDate;
    @Column(name = "screening_time")
    private LocalTime screeningTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LanguageOption getLanguageOption() {
        return languageOption;
    }

    public void setLanguageOption(LanguageOption languageOption) {
        this.languageOption = languageOption;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public BigDecimal getPriceWithDiscount(int discount){
        return price.subtract(price.multiply(new BigDecimal(discount)).divide(new BigDecimal(100)));
    }
}