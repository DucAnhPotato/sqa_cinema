package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByAuditorium_Id(Long id);
}
