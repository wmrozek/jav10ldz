package com.sda.cinema.administration.spring;

import com.sda.cinema.model.Genre;
import com.sda.cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class SpringAdminQuery {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Movie getMovieById(int id){
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movies",
                new Object[]{},
                new RowMapper(){

                    @Override
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
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
                        return movie;
                    }
                });
        System.out.println(movies);
        return movies.get(0);
    }
}
