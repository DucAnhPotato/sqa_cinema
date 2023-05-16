package com.spring.sqa_fullstack.controller.user;

import com.spring.sqa_fullstack.model.*;
import com.spring.sqa_fullstack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/booking")
public class UserBookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/movie/{id}")
    public String chooseSchedule(Model model,
                                @PathVariable(name = "id") Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        List<Schedule> schedules = scheduleService.getSchedulesByMovieId(movieId);
        model.addAttribute("schedules", schedules);
        model.addAttribute("movie", movie);
        return "user/userBookingMovie";
    }

    @RequestMapping("/schedule/{scheduleId}")
    public String chooseSeats(Model model,
                              @PathVariable(name = "scheduleId") Long scheduleId) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        Movie movie = schedule.getMovie();
        Auditorium auditorium = schedule.getAuditorium();
        List<Seat> seats = seatService.getSeatsByAuditoriumId(auditorium.getId());
        model.addAttribute("seats", seats);
        model.addAttribute("movie", movie);
        model.addAttribute("schedule", schedule);
        model.addAttribute("auditorium", auditorium);
        return "user/userBookingSchedule";
    }

    @RequestMapping("/success")
    public String confirmBooking(Model model,
                                @RequestParam(name = "movieId") Long movieId,
                                @RequestParam(name = "startDate")LocalDate startDate,
                                @RequestParam(name = "startTime")LocalTime startTime,
                                @RequestParam(name = "auditorium") Long auditoriumId,
                                @RequestParam(name = "seats") List<Long> seatsId) {
        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setBookingTime(LocalTime.now());
        booking.setUser(userService.getUserById(2L));
        bookingService.saveBooking(booking);

        Schedule schedule = scheduleService.getSchedule(movieId, auditoriumId, startDate, startTime);

        List<Ticket> tickets = new ArrayList<>();

        List<Seat> seats = new ArrayList<>();
        for (Long id : seatsId) {
            Seat seat = seatService.getSeatById(id);
            seat.setIsSeated(1);
            seats.add(seat);
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setBooking(booking);
            ticket.setSchedule(schedule);
            ticketService.saveTicket(ticket);
            tickets.add(ticket);
        }
        model.addAttribute("booking", booking);
        model.addAttribute("tickets", tickets);
        model.addAttribute("seats", seats);
        model.addAttribute("schedule", schedule);
        return "user/userBookingSuccess";
    }
}
