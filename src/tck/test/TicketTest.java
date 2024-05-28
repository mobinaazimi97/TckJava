package tck.test;

import tck.model.bl.ResponseBl;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.time.LocalDateTime;

public class TicketTest {
    public static void main(String[] args) throws Exception {
            System.out.println(TicketBl.getTicketBl().save(
                    Ticket.builder()
                            .id(1)
                            .group(Group.Materiel)
                            .person(Person.builder().id(1).build())
                            .title("")
                            .text("")
                            .ticketDateTime(LocalDateTime.now())
                            .status(Status.answer)
                            .build()
            ));
        }
    }

