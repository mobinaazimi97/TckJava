package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.NoPersonFoundException;
import tck.controller.exceptions.NoTicketFoundException;
import tck.model.da.PersonDa;
import tck.model.da.TicketDa;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;
import tck.model.tool.CRUD;

import java.time.LocalDate;
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
                for (Ticket ticket : ticketList) {
                    ticket.setPerson(PersonBl.getPersonBl().findById(ticket.getPerson().getId()));
                }
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
                int personId = ticket.getPerson().getId();
                Person person = PersonBl.getPersonBl().findById(personId);
                //   ticket.setPerson(PersonBl.getPersonBl().findById(ticket.getPerson().getId()));
                ticket.setPerson(person);
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public List<Ticket> findByPersonFamily(String family) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            List<Ticket> ticketList = ticketDa.findByPersonFamily(family);
            if (!ticketList.isEmpty()) {
                List<Person> person = PersonBl.getPersonBl().findByFamily(family);
                Ticket ticket = ticketDa.findByPersonId(person.get());                         //   TODO
                ticket.setPerson(PersonBl.getPersonBl().findById(ticket.getPerson().getId()));
                return ticketList;
            }else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByStatus(Status status) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findByStatus(Status.valueOf(String.valueOf(status)));
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByGroup(Group group) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findByGroup(Group.valueOf(String.valueOf(group)));
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByTitle(String title) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findByTitle(title);
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByText(String text) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findByText(text);
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByDateRange(LocalDate dateRange) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Ticket ticket = ticketDa.findByDateRange(dateRange);
            if (ticket != null) {
                return ticket;
            } else {
                throw new NoTicketFoundException();
            }
        }
    }

    public Ticket findByUsername(String username) throws Exception {
        try (TicketDa ticketDa = new TicketDa()) {
            Person person = PersonBl.getPersonBl().findByUsername(username);
            Ticket ticket = ticketDa.findByPersonId(person.getId());
            ticket.setPerson(PersonBl.getPersonBl().findById(ticket.getPerson().getId()));
            return ticket;
        }
    }
}