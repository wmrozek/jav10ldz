package com.sda.cinema.ui;

import com.sda.cinema.administration.spring.SpringAdminQuery;
import com.sda.cinema.orm.model.Movie;
import com.sda.cinema.orm.model.Screening;
import com.sda.cinema.orm.model.Ticket;
import com.sda.cinema.orm.model.TicketType;
import com.sda.cinema.orm.repository.MovieRepository;
import com.sda.cinema.orm.repository.ScreeningRepository;
import com.sda.cinema.orm.repository.TicketRepository;
import com.sda.cinema.orm.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class UserInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public void ticketBooking() {
        System.out.println("Witaj w kinie: wybierz film:");
        Movie movie = getMovie();
        System.out.println("Wybierz seans: ");
        Screening screening = getScreening(movie);
        System.out.println("Wybierz typ biletu");
        TicketType ticketType = getTicketType();
        Ticket ticket = addTicket(screening, ticketType);
        System.out.println(String.format("Kupiłeś bilet na seans: %s, %s %s. Cena biletu: %s",
                movie.getTitle(),
                screening.getScreeningDate().toString(),
                screening.getScreeningTime().toString(),
                screening.getPriceWithDiscount(ticketType.getPercentageDiscount())));
    }

    private Ticket addTicket(Screening screening, TicketType ticketType){
        Ticket ticket = new Ticket();
        ticket.setSaleDate(LocalDateTime.now());
        ticket.setScreening(screening);
        ticket.setTicketType(ticketType);
        ticketRepository.save(ticket);
        return ticket;
    }

    private TicketType getTicketType() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        for (TicketType ticketType : ticketTypes) {
            System.out.println(ticketType.getId() + " " +ticketType.getTypeName());
        }
        System.out.println("Podaj id biletu");
        Integer idTicket = getInt();
        if (idTicket == null) {
            System.out.println("Nie wybrałeś poprawnie - do widzenia!");
            System.exit(0);
        }
        return ticketTypes.stream().filter(ticketType -> ticketType.getId() == idTicket).findFirst().orElseThrow(IllegalStateException::new);
    }

    private Screening getScreening(Movie movie) {
        List<Screening> screenings = screeningRepository.findByMovie(movie);
        if (screenings.isEmpty()) {
            System.out.println("Nie ma seansu, sorry.");
            throw new IllegalStateException("Brak seansu");
        }
        for (Screening screening : screenings) {
            System.out.println(screening.getId() + ": " + screening.getScreeningDate().toString() + " " + screening.getScreeningTime().toString());
        }
        System.out.println("Podaj id seasu: ");
        Integer screeningId = getInt();
        if (screeningId == null) {
            System.out.println("Nie wybrałeś poprawnie - do widzenia!");
            System.exit(0);
        }
        return screenings.stream().filter(screening -> screening.getId() == screeningId).findFirst().orElseThrow(IllegalStateException::new);
    }

    private Movie getMovie() {
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

    private Integer getInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
