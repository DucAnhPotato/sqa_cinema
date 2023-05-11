package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
}
