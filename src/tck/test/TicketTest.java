package tck.test;

import tck.model.bl.PersonBl;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;

import java.time.LocalDateTime;

public class TicketTest {
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
                      .ticketDateTime(LocalDateTime.now())
                      .text("answered")
                      .title("requiery")
                      .person(person)
                      .build();
        PersonBl.getPersonBl().save(person);
        System.out.println(person);
        TicketBl.getTicketBl().save(ticket);
        System.out.println(ticket);
  //      System.out.println(TicketBl.getTicketBl().findById(1));
    }
}
