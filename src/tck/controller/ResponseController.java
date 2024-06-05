package tck.controller;

import tck.model.bl.ResponseBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/Response")
public class ResponseController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse() {
        Response response = Response
                .builder()
                .id(1)
//            .person(Person.builder().id(getInt("PERSON_ID")).build())             //TODO
//                .ticket(Ticket.builder().id(getInt("TICKET_ID")).build())             //TODO
                .answer("we sent your answer")
                .dateTime(LocalDateTime.now())
                .status(Status.answer)
                .build();
//        return Response.status(201).entity(response).build();             //TODO

    }

}
