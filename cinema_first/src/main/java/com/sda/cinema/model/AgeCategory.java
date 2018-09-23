package com.sda.cinema.model;

public class AgeCategory {
    private int id;
    private String categoryName;
    private int limitAge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLimitAge() {
        return limitAge;
    }

    public void setLimitAge(int limitAge) {
        this.limitAge = limitAge;
    }
}
