package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Auditorium;
import com.spring.sqa_fullstack.model.Movie;
import com.spring.sqa_fullstack.model.Schedule;
import com.spring.sqa_fullstack.service.AuditoriumService;
import com.spring.sqa_fullstack.service.MovieService;
import com.spring.sqa_fullstack.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private MovieService movieService;

    @RequestMapping()
    public String allSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "admin/schedule/schedules";
    }

    @RequestMapping("/{id}")
    public String getScheduleById(@PathVariable(name = "id") Long id,
                                  Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        return "admin/schedule/detailSchedule";
    }

    @RequestMapping("/add")
    public String addSchedule(Model model) {
        Schedule schedule = new Schedule();
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("schedule", schedule);
        model.addAttribute("movies", movies);
        model.addAttribute("auditoriums", auditoriums);
        return "admin/schedule/addSchedule";
    }

    @RequestMapping("/update/{id}")
    public String updateSchedule(@PathVariable(name = "id") Long id,
                                 Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("schedule", schedule);
        model.addAttribute("movies", movies);
        model.addAttribute("auditoriums", auditoriums);
        return "admin/schedule/updateSchedule";
    }

    @RequestMapping("/save")
    public String save(
            @RequestParam(name = "id", required = false) Long id,
            @Valid Schedule schedule,
            Movie movie,
            Auditorium auditorium,
            BindingResult result) {
        if (result.hasErrors()) {
            if (id == null) {
                return "admin/schedule/addSchedule";
            } else {
                return "admin/schedule/updateSchedule";
            }
        }
        schedule.setId(id);
        schedule.setAuditorium(auditorium);
        schedule.setMovie(movie);
        scheduleService.saveSchedule(schedule);
        return "redirect:/admin/schedules";
    }

    @RequestMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable(name = "id") Long id) {
        scheduleService.deleteScheduleById(id);
        return "redirect:/admin/schedules";
    }
}
