package com.sda.cinema.main;

import com.sda.cinema.administration.spring.SpringAdminQuery;
import com.sda.cinema.model.ScreeningInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.sda")
public class CinemaSpringMain implements CommandLineRunner {

    @Autowired
    private SpringAdminQuery springAdminQuery;

    public static void main(String args[]) {
        SpringApplication.run(CinemaSpringMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(springAdminQuery.getMovieById(2));
        String title = "Shrek";
        LocalDate fromDate = LocalDate.of(2018,8,20);
        LocalDate toDate = LocalDate.of(2018,10,31);

        List<ScreeningInfo> screeningInfoList = springAdminQuery.getScreeningsInRange(fromDate,toDate);
        for (ScreeningInfo screeningInfo : screeningInfoList) {
            System.out.println("NAZWA FILMU: " + screeningInfo.getTitle());
            System.out.println(screeningInfo.getScreeningDate() + " " + screeningInfo.getScreeningTime());
            System.out.println(screeningInfo.getGenre() + "; " + screeningInfo.getAgeCategory());
        }
    }

}
