package com.sda.cinema.main;

import com.sda.cinema.administration.AdminQuery;
import com.sda.cinema.model.Movie;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.List;

public class CinemaMain {
    public static void main(String[] args) {
        String titleToSearch = "Shrek";
        Connection conn = DatabaseConnection.getConnection();
//            getMovies(conn);
//            getMovieByTitle(titleToSearch, conn);
//        UseCases.showMovies(conn);
        UseCases.showRepertoireByDate(conn, "2018-09-15");

        UseCases.showRepertoireByDate(conn, "2018-09-20");
        DatabaseConnection.closeOnExit(conn);
    }

    private static void getMovieByTitle(String titleToSearch, Connection conn) throws SQLException {
        Statement statement = conn.prepareStatement("SELECT title FROM movies WHERE title = ? " +
                " AND id > ?");
        ((PreparedStatement) statement).setString(1, titleToSearch);

        ((PreparedStatement) statement).setInt(2, 0);
        ((PreparedStatement) statement).execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("title"));
        }
    }

    private static void getMovies(Connection conn) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("SELECT id, title FROM movies;");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            String title = resultSet.getString("title");
            Integer id = resultSet.getInt("id");
            System.out.println("Film: " + title + ", id = " + id);
        }
    }


}
