package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getSchedulesByMovie_Id(Long movieId);
    @Query("select distinct s.startTime from Schedule s " +
            "where s.movie.id = :movie_id " +
            "and s.date = :schedule_date")
    List<LocalTime> getStartTimesByMovie_IdAndDate(@Param("movie_id") Long movieId,
                                                      @Param("schedule_date")LocalDate scheduleDate);

    @Query("select s.date from Schedule s " +
            "where s.movie.id = :movie_id")
    List<LocalDate> getDatesByMovie_Id(@Param("movie_id") Long movieId);

    Schedule getScheduleByMovie_IdAndAuditorium_IdAndDateAndStartTime(Long movieId, Long auditoriumId, LocalDate startDate, LocalTime startTime);

}
