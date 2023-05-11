package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
