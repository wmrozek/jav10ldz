package com.sda.cinema.main;

import com.sda.cinema.administration.AdminQuery;
import com.sda.cinema.model.Movie;
import com.sda.cinema.model.Screening;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class UseCases {
    public static void showMovies(Connection connection){
        List<Movie> movieList = AdminQuery.getMovies(connection);
        for (Movie movie : movieList){
            System.out.println("FILM: "+movie.getTitle());
            System.out.println("REŻYSER: "+movie.getDirector());
            System.out.println("OBSADA: "+movie.getActors());
            System.out.println("ILOSC SEANSÓW: "+movie.getScreenings().size());
            System.out.println("--------------------");
        }
    }

    public static void showRepertoireByDate(Connection connection, String date){
        LocalDate localDate = LocalDate.parse(date);
        List<Screening> repertoire = AdminQuery.getScreeningsByDate(connection, localDate);
        System.out.println("REPERTUAR Z DNIA: "+date);
        for (Screening screening : repertoire){
            System.out.println("FILM: "+screening.getMovie().getTitle());
            System.out.println("GODZINA: "+screening.getScreeningTime());
            System.out.println("-------------------------------");
        }
        System.out.println("KONIEC");

    }


}
