package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
