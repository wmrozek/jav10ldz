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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TicketBookingUserInterface {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private UserInterface userInterface;

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    public void ticketBooking() {
        System.out.println("Witaj w kinie: wybierz film:");
        Movie movie = userInterface.getMovie();
        System.out.println("Wybierz seans: ");
        Screening screening = getScreening(movie);
        System.out.println("Wybierz typ biletu");
        TicketType ticketType = getTicketType();
        System.out.println("Podaj ilość biletów");
        int ticketCount = userInterface.getInt();
        ticketCount = checkAvailableTickets(screening,ticketCount);
        List<Ticket> tickets = addTickets(screening, ticketType, ticketCount);
        System.out.println(String.format("Kupiłeś %d biletów na seans: %s, %s %s. Cena biletu: %s",
                ticketCount,
                movie.getTitle(),
                screening.getScreeningDate().toString(),
                screening.getScreeningTime().toString(),
                screening.getPriceWithDiscount(ticketType.getPercentageDiscount())));

    }

    private int checkAvailableTickets(Screening screening, int ticketCount){
        int numberOfSeats = screening.getCinemaRoom().getNumberOfSeats();
        int soldTickets = ticketRepository.getTicketCountByScreening(screening);
        int freeSeats = numberOfSeats - soldTickets;
        if (freeSeats>=ticketCount){
            return ticketCount;
        }
        else if (freeSeats==0){
            System.out.println("Brak wolnych miejsc!");
            throw new IllegalStateException();
        }
        else {
            System.out.println("Dostępne tylko "+ freeSeats + " wolnych miejsc. Czy chcesz dokonać zakupu?(T/N)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("T")){
                return freeSeats;
            }
            else {
                throw new IllegalStateException();
            }
        }
    }

    private Ticket addTicket(Screening screening, TicketType ticketType) {
        Ticket ticket = new Ticket();
        ticket.setSaleDate(LocalDateTime.now());
        ticket.setScreening(screening);
        ticket.setTicketType(ticketType);
        ticketRepository.save(ticket);
        return ticket;
    }

    private List<Ticket> addTickets(Screening screening, TicketType ticketType, int ticketCount) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(addTicket(screening, ticketType));
        }
        return tickets;
    }


    private TicketType getTicketType() {
        List<TicketType> ticketTypes = ticketTypeRepository.findAll();
        for (TicketType ticketType : ticketTypes) {
            System.out.println(ticketType.getId() + " " + ticketType.getTypeName());
        }
        System.out.println("Podaj id biletu");
        Integer idTicket = userInterface.getInt();
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
        Integer screeningId = userInterface.getInt();
        if (screeningId == null) {
            System.out.println("Nie wybrałeś poprawnie - do widzenia!");
            System.exit(0);
        }
        return screenings.stream().filter(screening -> screening.getId() == screeningId).findFirst().orElseThrow(IllegalStateException::new);
    }




}
