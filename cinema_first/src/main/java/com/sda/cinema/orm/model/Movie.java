package com.sda.cinema.orm.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "director")
    private String director;
    @Column(name = "duration")
    private LocalTime duration;
    @Column(name = "actors")
    private String actors;
    @Column(name = "movie_description")
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private Genre genre;
    @OneToOne
    @JoinColumn(name = "id_age_category")
    private AgeCategory ageCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }
}
