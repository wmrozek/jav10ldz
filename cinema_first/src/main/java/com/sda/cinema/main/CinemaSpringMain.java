package com.sda.cinema.main;

import com.sda.cinema.administration.spring.SpringAdminQuery;
import com.sda.cinema.model.ScreeningInfo;
import com.sda.cinema.orm.model.Movie;
import com.sda.cinema.orm.repository.MovieRepository;
import com.sda.cinema.ui.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.sda")
@EnableJpaRepositories(basePackages = {"com.sda"})
@EntityScan("com.sda")
public class CinemaSpringMain implements CommandLineRunner {

    @Autowired
    private SpringAdminQuery springAdminQuery;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserInterface userInterface;

    public static void main(String args[]) {
        SpringApplication.run(CinemaSpringMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(springAdminQuery.getMovieById(2));
//        String title = "Shrek";
//        LocalDate fromDate = LocalDate.of(2018,8,20);
//        LocalDate toDate = LocalDate.of(2018,10,31);
//
//        List<ScreeningInfo> screeningInfoList = springAdminQuery.getScreeningsInRange(fromDate,toDate);
//        for (ScreeningInfo screeningInfo : screeningInfoList) {
//            System.out.println("NAZWA FILMU: " + screeningInfo.getTitle());
//            System.out.println(screeningInfo.getScreeningDate() + " " + screeningInfo.getScreeningTime());
//            System.out.println(screeningInfo.getGenre() + "; " + screeningInfo.getAgeCategory());
//        }


//        Movie newMovie = new Movie();
//        newMovie.setTitle("Psy");
//        newMovie.setActors("Bogusław Linda");
//        newMovie.setDirector("Władysław Pasikowski");
//        newMovie.setDescription("Przygoda o psach");
//        newMovie.setDuration(LocalTime.of(1,50));
//        newMovie.setReleaseDate(LocalDate.of(1992, 2,3));
//        movieRepository.save(newMovie);


//        Iterable<Movie> movies = movieRepository.findAll();
//        for (Movie movie : movies) {
//            System.out.println(movie.getTitle());
//            System.out.println(movie.getGenre().getName());
//        }
        userInterface.ticketBooking();
    }

}
