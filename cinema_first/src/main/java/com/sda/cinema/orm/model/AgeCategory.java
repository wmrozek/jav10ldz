package com.sda.cinema.orm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "age_categories")
public class AgeCategory {

    @Id
    private int id;
    @Column(name = "age_limit")
    private Integer ageLimit;
    @Column(name = "limit_description")
    private String limitDescription;

    @Override
    public String toString() {
        return "AgeCategories{" +
                "id=" + id +
                ", ageLimit=" + ageLimit +
                ", limitDescription='" + limitDescription + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getLimitDescription() {
        return limitDescription;
    }

    public void setLimitDescription(String limitDescription) {
        this.limitDescription = limitDescription;
    }
}