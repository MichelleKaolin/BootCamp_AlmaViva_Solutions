package com.michelle.tickets.controller;

import com.michelle.tickets.model.Ticket;
import com.michelle.tickets.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public Ticket criar(@RequestBody Ticket ticket) {
        return service.criar(ticket);
    }

    @GetMapping
    public List<Ticket> listar() {
        return service.listar();
    }
}