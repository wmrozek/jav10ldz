package com.sda.cinema.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScreeningInfo {
    private String title;
    private LocalTime duration;
    private LocalDate screeningDate;
    private LocalTime screeningTime;
    private int soldTickets;
    private String cinemaRoom;
    private String language;
    private String genre;
    private String ageCategory;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
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

    public int getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets = soldTickets;
    }

    public String getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(String cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
