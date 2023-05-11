package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
