package tck.test;

import tck.model.bl.PersonBl;
import tck.model.bl.ResponseBl;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;

import java.time.LocalDate;

public class ResponseTest {
    public static void main(String[] args) throws Exception {
        Person person=
                Person.builder()
                        .id(1)
                        .name("mobina")
                        .family("azimi")
                        .phoneNumber("091943346556")
                        .email("www.mobina.com")
                        .username("mobi123")
                        .password("1234")
                        .role(Role.Admin)
                        .enabled(true)
                        .build();
        Ticket ticket=
                Ticket.builder()
                        .id(1)
                        .group(Group.Materiel)
                        .status(Status.answer)
                        .ticketDate(LocalDate.now())
                        .text("answered")
                        .title("request")
                        .person(person)
                        .build();
        Response response=
        Response.builder()
                        .id(1)
                        .ticket(Ticket.builder().id(1).build())
                        .person(Person.builder().id(1).build())
                        .answer("hello")
                        .date(LocalDate.now())
                        .build();
        PersonBl.getPersonBl().save(person);
        System.out.println(person);
        TicketBl.getTicketBl().findByUsername("mobi123");
        System.out.println(ticket);
        ResponseBl.getResponseBl().save(response);
        System.out.println(response);
  //      System.out.println(TicketBl.getTicketBl().findById(1));
    }
    }

//                Response.builder()
//                        .id(1)
//                        .ticket(Ticket.builder().id(1).build())
//                        .person(Person.builder().id(1).build())
//                        .answer("hello")
//                        .date(LocalDate.now())
//                        .build()
//        ));

