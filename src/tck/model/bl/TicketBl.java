package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.NoResponseFoundException;
import tck.controller.exceptions.NoTicketFoundException;
import tck.model.da.ResponseDa;
import tck.model.da.TicketDa;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;

import java.util.List;

public class TicketBl implements CRUD<Ticket> {
    @Getter
    private static TicketBl ticketBl = new TicketBl();

    private TicketBl() {
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            ticketDa.save(ticket);
            return ticket;
        }
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            if (ticketDa.findById(ticket.getId()) != null) {
                ticketDa.edit(ticket);
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    @Override
    public Ticket remove(int id) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findById(id);
            if (ticket != null) {
                ticketDa.remove(id);
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            List<Ticket> ticketList = ticketDa.findAll();
            if (!ticketList.isEmpty()) {
                return ticketList;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    @Override
    public Ticket findById(int id) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findById(id);
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }
}
