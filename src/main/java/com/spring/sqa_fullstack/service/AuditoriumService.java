package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.controller.admin.AuditoriumController;
import com.spring.sqa_fullstack.model.Auditorium;
import com.spring.sqa_fullstack.model.Seat;
import com.spring.sqa_fullstack.repository.AuditoriumRepository;
import com.spring.sqa_fullstack.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumService {
    @Autowired
    private AuditoriumRepository auditoriumRepository;
    @Autowired
    private SeatRepository seatRepository;

    public List<Auditorium> getAllAuditoriums() {
        return auditoriumRepository.findAll();
    }

    public void saveAuditorium(Auditorium auditorium) {
        auditoriumRepository.save(auditorium);
        for (int i = 0; i < auditorium.getSeatCount(); i++) {
            Seat seat = new Seat();
            seat.setIsSeated(0);
            seat.setAuditorium(auditorium);
            seatRepository.save(seat);
        }
    }

    public Auditorium getAuditoriumById(Long id) {
        return auditoriumRepository.findById(id).orElse(null);
    }

    public void deleteAuditoriumById(Long id) {
        auditoriumRepository.deleteById(id);
    }
}
