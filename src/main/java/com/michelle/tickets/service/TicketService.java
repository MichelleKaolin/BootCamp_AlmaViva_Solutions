package com.michelle.tickets.service;

import com.michelle.tickets.model.Status;
import com.michelle.tickets.model.Ticket;
import com.michelle.tickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket criar(Ticket ticket) {
        ticket.setStatus(Status.ABERTO);
        return repository.save(ticket);
    }

    public List<Ticket> listar() {
        return repository.findAll();
    }
}