package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.model.Booking;
import com.spring.sqa_fullstack.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

}
