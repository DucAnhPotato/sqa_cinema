package com.spring.sqa_fullstack.service;

import com.spring.sqa_fullstack.model.Auditorium;
import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.model.Schedule;
import com.spring.sqa_fullstack.repository.AuditoriumRepository;
import com.spring.sqa_fullstack.repository.MovieRepository;
import com.spring.sqa_fullstack.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

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

    public List<Schedule> getSchedulesByMovieId(Long id) {
        return scheduleRepository.getSchedulesByMovie_Id(id);
    }

    public List<LocalTime> getStartTimesByMovieIdAndDate(Long movieId, LocalDate scheduleDate) {
        return scheduleRepository.getStartTimesByMovie_IdAndDate(movieId, scheduleDate);
    }

    public List<LocalDate> getDatesByMovieId(Long movieId) {
        return scheduleRepository.getDatesByMovie_Id(movieId);
    }

    public Schedule getSchedule(Long movieId, Long auditoriumId, LocalDate startDate, LocalTime startTime) {
        return scheduleRepository.getScheduleByMovie_IdAndAuditorium_IdAndDateAndStartTime(movieId, auditoriumId, startDate, startTime);
    }
}
