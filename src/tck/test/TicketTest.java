package tck.test;

import tck.model.bl.PersonBl;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;

import java.time.LocalDate;

public class TicketTest {
    public static void main(String[] args) throws Exception {
        System.out.println(TicketBl.getTicketBl().findByPersonId(1));
//      Person person=
//               Person.builder()
//                .id(1)
//                .name("mobina")
//                .family("azimi")
//                .phoneNumber("091943346556")
//                .email("www.mobina.com")
//                .username("mobi123")
//                .password("1234")
//                .role(Role.Admin)
//                .enabled(true)
//                .build();
//      Ticket ticket=
//              Ticket.builder()
//                      .id(1)
//                      .group(Group.Materiel)
//                      .ticketDate(LocalDate.now())
//                      .text("answered")
//                      .title("requiery")
//                      .person(person)
//                      .build();
//        PersonBl.getPersonBl().save(person);
//        System.out.println(person);
//        TicketBl.getTicketBl().save(ticket);
//        System.out.println(ticket);
 //       System.out.println(TicketBl.getTicketBl().findById(1));
  }
}
