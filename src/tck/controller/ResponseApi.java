package tck.controller;

import com.google.gson.Gson;
import tck.model.bl.PersonBl;
import tck.model.bl.ResponseBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/Response")
public class ResponseApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse() {
        Response response = Response
                .builder()
                .id(1)
                .person()             //TODO
                .ticket()             //TODO
                .answer("we sent your answer")
                .dateTime(LocalDateTime.now())
                .status(Status.answer)
                .build();
        return Response.status(201).entity(response).build();             //TODO

    }

    @POST
    public Response save(
            @QueryParam("id") int id,
            @QueryParam("ticketId") int ticketId,           //TODO : int  ticketId ?
            @QueryParam("personId") int personId,
            @QueryParam("answer") String answer,
            @QueryParam("status") Status status,
            @QueryParam("dateTime") LocalDateTime dateTime) throws Exception {         //TODO
        return Response
                .builder()
                .id(1)
                .person(PersonBl.getPersonBl().findById(personId))             //TODO
                .ticket(Ticket.builder().id(getInt("TICKET_ID")).build())             //TODO
                .answer("we sent your answer")
                .dateTime(LocalDateTime.now())
                .status(Status.answer)
                .build();
        ResponseBl.getResponseBl().save(getResponse());             // TODO :  is it right??
    }

    @PUT
    public Response edit(
            @QueryParam("id") int id,
            @QueryParam("ticketId") int ticketId,           //TODO : int  ticketId ?
            @QueryParam("personId") int personId,
            @QueryParam("answer") String answer,
            @QueryParam("status") Status status,
            @QueryParam("dateTime") LocalDateTime dateTime) throws Exception {         //TODO
        return Response
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID")).build())             //TODO
                .ticket(Ticket.builder().id(getInt("TICKET_ID")).build())             //TODO
                .answer("we sent your answer")
                .dateTime(LocalDateTime.now())
                .status(Status.answer)
                .build();
        ResponseBl.getResponseBl().edit(getResponse());             // TODO :  is it right??
    }

    @GET
    public String find() {          // TODO : EVERYWHERE??
        return "Find";
    }

    @GET
    @Path("/all")
    public String findAll() throws Exception {
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findAll();
        responseList.add(response);                   //TODO
        Gson gson = new Gson();
        return gson.toJson(responseList);
    }

    @GET
    @Path("/all{id}")
    public String findById(@PathParam("id") int id) throws Exception {
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(getResponse().getTicket().setId(id))
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();     //TODO
        ResponseBl.getResponseBl().findById(response.getId());      // TODO ; TRUE ??
        Gson gson = new Gson();
        return gson.toJson(responseList.get(id));
    }

    @Path("/all/{personId}")            //TODO  :   personId?
    public String findByPersonId(@PathParam("personId") int id) throws Exception {
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(getResponse().getTicket().setId(id))              // TODO
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findByPersonId(id);
        Gson gson = new Gson();
        return gson.toJson(responseList);
    }

    @Path("/all/{ticketId}")            //TODO  :   personId?
    public String findByTicketId(@PathParam("ticketId") int id) throws Exception {
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(Ticket.builder().id(id).build())                        // TODO
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findByTicketId(id);
        Gson gson = new Gson();
        return gson.toJson(responseList);
    }

    @Path("/all/{status}")
    public String findByStatus(@PathParam("status") Status status) throws Exception {
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(Ticket.builder().id().build())                        // TODO
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findByStatus(status);
        responseList.add(response);
        Gson gson = new Gson();
        return gson.toJson(responseList.get(Status.valueOf()));         //TODO
    }

    @Path("/all/{answer}")
    public String findByAnswer(@PathParam("answer") String answer) throws Exception {
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(Ticket.builder().id().build())                        // TODO
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findByAnswer(answer);
        responseList.add(response);
        Gson gson = new Gson();
        return gson.toJson(responseList);           //TODO ??
    }

    @Path("/all/{dateTime}")
    public String findByDateTime(@PathParam("dateTime") LocalDateTime dateTime) throws Exception {  //TODO
        Response response = Response
                .builder()
                .id(1)
                .person(getResponse().getPerson().setId(id))            //TODO
                .ticket(Ticket.builder().id().build())                        // TODO
                .answer("we sent your answer")
                .status(Status.answer)
                .dateTime(LocalDateTime.now())
                .build();
        List<Response> responseList = new ArrayList<>();
        ResponseBl.getResponseBl().findByDateTime(dateTime);            //TODO
        responseList.add(response);
        Gson gson = new Gson();
        return gson.toJson(responseList);                       //TODO
    }
}



