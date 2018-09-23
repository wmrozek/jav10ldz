package com.sda.cinema.administration;

import com.sda.cinema.model.Genre;
import com.sda.cinema.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AdminCommand {
    private static final String INSERT_MOVIE = "INSERT INTO movies(title, " +
            "release_date, " +
            "actors, " +
            "director, " +
            "duration, " +
            "movie_description, " +
            "id_genre) " +
            "VALUES(?,?,?,?,?,?,?);";

    public static boolean addMovie(Connection connection,
                                   String title,
                                   String director,
                                   String actors,
                                   String description,
                                   String releaseDate,
                                   String duration,
                                   String genreName) {
        try {
            Genre genre = AdminQuery.getGenreByName(connection, genreName);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, releaseDate);
            preparedStatement.setString(3, actors);
            preparedStatement.setString(4, director);
            preparedStatement.setString(5, duration);
            preparedStatement.setString(6, description);
            if (genre != null) {
                preparedStatement.setInt(7, genre.getId());
            } else {
                preparedStatement.setNull(7, Types.INTEGER);
            }
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
