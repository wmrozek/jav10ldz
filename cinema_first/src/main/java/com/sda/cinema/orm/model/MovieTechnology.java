package com.sda.cinema.orm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie_technologies")
public class MovieTechnology {
    @Id
    private int id;
    @Column(name = "technology_name")
    private String technologhyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnologhyName() {
        return technologhyName;
    }

    public void setTechnologhyName(String technologhyName) {
        this.technologhyName = technologhyName;
    }
}