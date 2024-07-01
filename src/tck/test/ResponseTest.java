package tck.test;

import tck.model.bl.PersonBl;
import tck.model.bl.ResponseBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;

import java.time.LocalDate;

public class ResponseTest {
    public static void main(String[] args) throws Exception {
        System.out.println(ResponseBl.getResponseBl().edit(
                Response.builder()
                        .id(1)
                        .ticket(Ticket.builder().id(1).build())
                        .person(Person.builder().id(1).build())
                        .answer("hello")
                        .date(LocalDate.now())
                        .build()
        ));
   }
}
