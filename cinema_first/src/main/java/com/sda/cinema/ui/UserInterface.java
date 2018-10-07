package com.sda.cinema.ui;

import com.sda.cinema.orm.model.Movie;
import com.sda.cinema.orm.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class UserInterface {
    @Autowired
    private MovieRepository movieRepository;
    private Scanner scanner = new Scanner(System.in);

     Movie getMovie() {
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.getId() + ": " + movie.getTitle());
        }
        System.out.println("Podaj id filmu: ");
        Integer movieId = getInt();
        if (movieId == null) {
            System.out.println("Nie wybrałeś poprawnie - do widzenia!");
            System.exit(0);
        }
        return movies.stream().filter(movie -> movie.getId() == movieId).findFirst().orElseThrow(IllegalStateException::new);
    }


    public Integer getInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
