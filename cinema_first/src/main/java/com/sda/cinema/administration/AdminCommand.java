package com.sda.cinema.administration;

import com.sda.cinema.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminCommand {
    private static final String INSERT_MOVIE = "INSERT INTO movies(title, release_date, actors, director, duration, movie_description, id_age_category, id_genre) " +
            "VALUES(?,?,?,?,?,?,?,?);";
    public static int addMovie(Connection connection, Movie movie){
        try {
            PreparedStatement prepStmt = connection.prepareStatement(INSERT_MOVIE);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
