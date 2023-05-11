package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Auditorium;
import com.spring.sqa_fullstack.model.Seat;
import com.spring.sqa_fullstack.service.AuditoriumService;
import com.spring.sqa_fullstack.service.SeatService;
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
@RequestMapping("/admin/auditoriums")
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private SeatService seatService;

    @RequestMapping()
    public String allAuditoriums(Model model) {
        List<Auditorium> auditoriums = auditoriumService.getAllAuditoriums();
        model.addAttribute("auditoriums", auditoriums);
        return "admin/auditorium/auditoriums";
    }

    @RequestMapping("/{id}")
    public String getAuditoriumById(@PathVariable(name = "id") Long id,
                                    Model model) {
        Auditorium auditorium = auditoriumService.getAuditoriumById(id);
        List<Seat> seats = seatService.getSeatsByAuditoriumId(id);
        model.addAttribute("auditorium", auditorium);
        model.addAttribute("seats", seats);
        return "admin/auditorium/detailAuditorium";
    }

    @RequestMapping("/add")
    public String addAuditorium(Model model) {
        Auditorium auditorium = new Auditorium();
        model.addAttribute("auditorium", auditorium);
        return "admin/auditorium/addAuditorium";
    }

    @RequestMapping("/save")
    public String save(
            @RequestParam(name = "id", required = false) Long id,
            @Valid Auditorium auditorium,
            BindingResult result) {
        if (result.hasErrors()) {
            if (id == null) {
                return "admin/auditorium/addAuditorium";
            }
        }
        auditorium.setId(id);
        auditoriumService.saveAuditorium(auditorium);
        return "redirect:/admin/auditoriums";
    }

    @RequestMapping("/delete/{id}")
    public String deleteAuditorium(@PathVariable(name = "id") Long id) {
        auditoriumService.deleteAuditoriumById(id);
        return "redirect:/admin/auditoriums";
    }
}
