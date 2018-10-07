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
    private String technologyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}