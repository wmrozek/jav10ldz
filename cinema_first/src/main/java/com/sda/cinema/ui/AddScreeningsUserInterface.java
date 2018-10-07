package com.sda.cinema.ui;


import com.sda.cinema.orm.model.CinemaRoom;
import com.sda.cinema.orm.model.LanguageOption;
import com.sda.cinema.orm.model.Movie;
import com.sda.cinema.orm.model.Screening;
import com.sda.cinema.orm.repository.CinemaRoomRepository;
import com.sda.cinema.orm.repository.LanguageOptionRepository;
import com.sda.cinema.orm.repository.ScreeningRepository;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class AddScreeningsUserInterface {

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private LanguageOptionRepository languageOptionRepository;
    @Autowired
    private ScreeningRepository screeningRepository;

    Scanner scanner = new Scanner(System.in);

    public void addScreenings() {
        System.out.println("Wybierz film");
        Movie movie = userInterface.getMovie();
        System.out.println("Podaj datę pierwszego seansu w formacie RRRR-MM-DD");
        LocalDate firstScreeningDate = parseDate(scanner.nextLine());
        System.out.println("Podaj datę ostatniego seansu w formacie RRRR-MM-DD");
        LocalDate lastScreeningDate = parseDate(scanner.nextLine());
        System.out.println("Co ile dni ma się wyświetlać?");
        int interval = userInterface.getInt();
        System.out.println("Wpisz cenę biletu");
        int price = userInterface.getInt();

        System.out.println("Wybierz id sali");
        CinemaRoom cinemaRoom = getCinemaRoom();
        System.out.println("O ktorej godzinie ma sie wyswietlac seans? Podaj w formacie hh:mm");
        String screeningTime = scanner.nextLine();
        LocalTime localTime = LocalTime.parse(screeningTime);
        System.out.println("Wybierz opcje jezykowa");
        LanguageOption userSelection = getLanguageOption();
        List<Screening> screeningsList = createScreeningsList(cinemaRoom, userSelection, movie, new BigDecimal(price), firstScreeningDate, lastScreeningDate, interval, localTime);
        int screeningsCount = screeningsList.size();
        System.out.println("Udało się stworzyć " + screeningsCount + " seansów");
    }

    private List<Screening> createScreeningsList(CinemaRoom cinemaRoom, LanguageOption languageOption,
                                                 Movie movie, BigDecimal price, LocalDate startScreeningSeason,
                                                 LocalDate endScreeningSeason, int screeningTimeInterval,
                                                 LocalTime screeningTime) {
        List<Screening> screenings = new ArrayList<>();
        LocalDate screeningDate = startScreeningSeason;
        while (screeningDate.isBefore(endScreeningSeason) && (screeningDate.isAfter(LocalDate.now()) || screeningDate.isEqual(LocalDate.now()))) {
            Screening screening = addScreening(cinemaRoom, languageOption, movie, price, screeningDate, screeningTime);
            if (screening != null) {
                screenings.add(screening);
            }
            screeningDate = screeningDate.plusDays(screeningTimeInterval);
        }
        return screenings;
    }

    private Screening addScreening(CinemaRoom cinemaRoom, LanguageOption languageOption,
                                   Movie movie, BigDecimal price, LocalDate screeningDate,
                                   LocalTime screeningTime) {
        Screening screening = new Screening();
        screening.setCinemaRoom(cinemaRoom);
        screening.setLanguageOption(languageOption);
        screening.setMovie(movie);
        screening.setPrice(price);
        screening.setScreeningDate(screeningDate);
        screening.setScreeningTime(screeningTime);
        if (isCinemaRoomAvailable(cinemaRoom, screeningDate, screeningTime, movie.getDuration())) {
            screeningRepository.save(screening);
            return screening;
        }
        return null;
    }

    private LanguageOption getLanguageOption() {
        List<LanguageOption> languageOptions = languageOptionRepository.findAll();
        for (LanguageOption languageOption : languageOptions) {
            System.out.println(languageOption.getId() + " " + languageOption.getAudio());
        }
        int choice = userInterface.getInt();
        return languageOptions.stream().filter(languageOption -> languageOption.getId() == choice).findFirst().
                orElseThrow(IllegalStateException::new);
    }

    private boolean isCinemaRoomAvailable(CinemaRoom cinemaRoom, LocalDate screeningDate, LocalTime screeningStartingTime, LocalTime duration) {
        List<Screening> thisScreenings = screeningRepository.findByScreeningDateAndCinemaRoom(screeningDate, cinemaRoom);
        LocalTime endScreeningTime = screeningStartingTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute());

        for (Screening thisScreening : thisScreenings) {
            LocalTime startScreeningTimeInLoop = thisScreening.getScreeningTime();
            LocalTime endScreeningTimeInLoop = startScreeningTimeInLoop.plusHours(thisScreening.getMovie().getDuration().getHour()).plusMinutes(thisScreening.getMovie().getDuration().getMinute());

            if (startScreeningTimeInLoop.isBefore(endScreeningTime) &&
                    startScreeningTimeInLoop.isAfter(screeningStartingTime)) {
                return false;
            } else if (endScreeningTimeInLoop.isBefore(endScreeningTime) && endScreeningTimeInLoop.isAfter(screeningStartingTime)) {
                return false;
            } else if (startScreeningTimeInLoop.isBefore(endScreeningTime) && endScreeningTimeInLoop.isAfter(screeningStartingTime)) {
                return false;
            }

        }
        return true;
    }

    private CinemaRoom getCinemaRoom() {
        List<CinemaRoom> cinemaRooms = cinemaRoomRepository.findAll();
        for (CinemaRoom cinemaRoom : cinemaRooms) {
            System.out.println(cinemaRoom.getId() + ": " +
                    cinemaRoom.getRoomName() +
                    ", liczba miejsc: " + cinemaRoom.getNumberOfSeats());
        }
        int choice = userInterface.getInt();
        return cinemaRooms.stream().filter(cinemaRoom -> cinemaRoom.getId() == choice).findFirst().
                orElseThrow(IllegalStateException::new);
    }

    private LocalDate parseDate(String string) {
        LocalDate localDate = LocalDate.parse(string);
        return localDate;
    }
}
