package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.model.Auditorium;
import com.spring.sqa_fullstack.model.Schedule;
import com.spring.sqa_fullstack.repository.AuditoriumRepository;
import com.spring.sqa_fullstack.repository.MovieRepository;
import com.spring.sqa_fullstack.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private AuditoriumRepository auditoriumRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
