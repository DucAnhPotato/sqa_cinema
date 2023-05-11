package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Booking;
import com.spring.sqa_fullstack.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @RequestMapping()
    public String allBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "admin/booking/bookings";
    }
}
