package com.sda.cinema.orm.model;

import javax.persistence.*;

@Entity
@Table(name = "cinema_rooms")
public class CinemaRoom {
    @Id
    private int id;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    @OneToOne
    @JoinColumn(name = "id_movie_technology")
    private MovieTechnology movieTechnology;

    public MovieTechnology getMovieTechnology() {
        return movieTechnology;
    }

    public void setMovieTechnology(MovieTechnology movieTechnology) {
        this.movieTechnology = movieTechnology;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}