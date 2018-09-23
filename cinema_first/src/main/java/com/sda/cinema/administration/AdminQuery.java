package com.sda.cinema.administration;

import com.sda.cinema.model.Genre;
import com.sda.cinema.model.Movie;
import com.sda.cinema.model.Screening;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminQuery {
    private static final String GET_MOVIES = "SELECT * FROM movies;";
    private static final String GET_GENRES = "SELECT * FROM genres;";
    private static final String GET_AGE_CATEGORIES = "SELECT * FROM age_categories;";
    private static final String GET_SCREENINGS_BY_DATE = "SELECT * FROM screenings WHERE screening_date = ?";
    private static final String GET_SCREENINGS_BY_MOVIE_ID = "SELECT * FROM screenings WHERE id_movie = ?";

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public static List<Screening> getScreeningsByDate(Connection connection, LocalDate date) {
        List<Movie> movies = getMovies(connection);
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SCREENINGS_BY_DATE);
            preparedStatement.setString(1, date.format(formatter));
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            List<Screening> results = new ArrayList<>();
            while(rs.next()){
                Screening screening = new Screening();
                screening.setId(rs.getInt("id"));
                String screeningDate = rs.getString("screening_date");
                if (screeningDate != null){
                    screening.setScreeningDate(LocalDate.parse(screeningDate));
                }
                String screeningTime = rs.getString("screening_time");
                if (screeningTime != null){
                    screening.setScreeningTime(LocalTime.parse(screeningTime));
                }
                Integer idMovie = rs.getInt("id_movie");
                Movie movie = movies.stream().filter(m -> m.getId() == idMovie).findFirst().get();
                screening.setMovie(movie);
                results.add(screening);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }

    }

    public static List<Movie> getMovies(Connection connection) {
        List<Genre> genres = getGenres(connection);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_MOVIES);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            List<Movie> results = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setActors(rs.getString("actors"));
                movie.setDescription(rs.getString("movie_description"));
                movie.setDirector(rs.getString("director"));
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                String releaseDate = rs.getString("release_date");
                if (releaseDate != null) {
                    movie.setReleaseDate(LocalDate.parse(releaseDate));
                }
                String duration = rs.getString("duration");
                if (duration != null) {
                    movie.setDuration(LocalTime.parse(duration));
                }
                Integer idGenre = rs.getInt("id_genre");
                if (idGenre > 0) {
                    Genre genre = genres.stream().filter(g -> g.getId() == idGenre).findFirst().get();
                    movie.setGenre(genre);
                }
                movie.setScreenings(getScreeningsByMovie(connection, movie));
                results.add(movie);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    private static List<Screening> getScreeningsByMovie(Connection connection, Movie movie){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SCREENINGS_BY_MOVIE_ID);
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            List<Screening> results = new ArrayList<>();
            while (rs.next()) {
                Screening screening = new Screening();
                screening.setId(rs.getInt("id"));
                String screeningDate = rs.getString("screening_date");
                if (screeningDate != null){
                    screening.setScreeningDate(LocalDate.parse(screeningDate));
                }
                String screeningTime = rs.getString("screening_time");
                if (screeningTime != null){
                    screening.setScreeningTime(LocalTime.parse(screeningTime));
                }
                screening.setMovie(movie);
                results.add(screening);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static List<Genre> getGenres(Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_GENRES);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            List<Genre> results = new ArrayList<>();
            while (rs.next()) {
                Genre genre = new Genre(rs.getInt("id"), rs.getString("genre_name"));
                results.add(genre);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
