package tck.test;

import tck.model.bl.PersonBl;
import tck.model.bl.ResponseBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResponseTest {
    public static void main(String[] args) throws Exception {
        System.out.println(ResponseBl.getResponseBl().save(
                Response.builder()
                        .id(1)
                        .ticket(Ticket.builder().id(1).build())
                        .person(Person.builder().id(1).build())
                        .answer("")
                        .date(LocalDate.now())
                        .build()
        ));
    }
    }
