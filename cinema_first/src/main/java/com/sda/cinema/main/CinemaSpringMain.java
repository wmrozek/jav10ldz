package com.sda.cinema.main;

import com.sda.cinema.administration.spring.SpringAdminQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sda")
public class CinemaSpringMain implements CommandLineRunner{

    @Autowired
    private SpringAdminQuery springAdminQuery;

    public static void main(String args[]) {
        SpringApplication.run(CinemaSpringMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(springAdminQuery.getMovieById(2));
    }
}
