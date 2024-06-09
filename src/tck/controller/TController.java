package tck.controller;

import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import java.time.LocalDateTime;
import java.util.List;

public class TController {
    public static void save(Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = new Ticket();
            ticket.setGroup(group).setPerson(person).setTitle(title).setText(text).setLocalDateTime(ticketDateTime);    //TODO
            TicketBl.getTicketBl().save(ticket);
            System.out.println("ticket saved.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void edit(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();            //TODO FOR DATE   &   SetId!
            TicketBl.getTicketBl().edit(ticket);
            System.out.println("ticket updated!.");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findAll(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            List<Ticket> ticketList = TicketBl.getTicketBl().findAll();            //TODO
            System.out.println("info:ticket found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findById(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findById(id);
            System.out.println("Ticket found by id!");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByPersonId(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findByPersonId(id);              //  TODO : CHECK !
            System.out.println("Person's Ticket found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByGroup(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findByGroup(group);          //TODO : CHECK!!
            System.out.println("ticket's group found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByTitle(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findByTitle(title);
            System.out.println("ticket's title found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByText(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findByText(text);
            System.out.println("ticket's text found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByDateRange(int id, Group group, Person person, String title, String text, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        //TODO FOR TIME !
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(person.setId(id)).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            TicketBl.getTicketBl().findByDateRange(startDateTime, endDateTime);     //TODO
            System.out.println("ticket's date range found");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByUsername(int id, Group group, Person person, String title, String text, LocalDateTime ticketDateTime) {
        try {
            Ticket ticket = Ticket.builder().id(id).group(Group.valueOf("group")).person(Person.builder().username(person.getUsername()).build()).title(title).text(text).ticketDateTime(LocalDateTime.now()).build();
            //  TODO FOR BUILD USERNAME !!!
            TicketBl.getTicketBl().findByUsername(person.getUsername());        // TODO!!!
            System.out.println("ticket found by username");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
