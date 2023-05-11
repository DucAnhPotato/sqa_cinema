package com.spring.sqa_fullstack.controller.admin;

import com.spring.sqa_fullstack.model.Ticket;
import com.spring.sqa_fullstack.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping()
    public String allTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "admin/ticket/tickets";
    }

    @RequestMapping("/{id}")
    public String getTicketById(@PathVariable(name = "id") Long id,
                                Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "admin/ticket/detailTicket";
    }
}
