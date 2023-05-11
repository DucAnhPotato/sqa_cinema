package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.model.Seat;
import com.spring.sqa_fullstack.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

    public List<Seat> getSeatsByAuditoriumId(Long id) {
        return seatRepository.findSeatsByAuditorium_Id(id);
    }
}
