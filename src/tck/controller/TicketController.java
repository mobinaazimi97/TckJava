package tck.controller;


import com.google.gson.Gson;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Path("/Ticket")
public class TicketController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTicket() {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .group(Group.Materiel)
                .person(Person.builder().id().build())          //TODO
                .title("query")
                .text("received")
                .ticketDateTime(LocalDateTime.now())        //TODO
                .build();
        return Response.status(201).entity(ticket).build();
    }

    @POST
    public Ticket save(
            @QueryParam("id") int id,
            @QueryParam("group") String group,
            @QueryParam("personId") int personId,
            @QueryParam("title") String title,
            @QueryParam("text") String text,
            @QueryParam("ticketDateTime") LocalDateTime ticketDateTime) throws Exception {         //TODO
      Ticket ticket =
              Ticket
                .builder()
                .id(1)
             //.group(Group.Materiel)                              //TODO ??
                .person(Person.builder().id().build())          //TODO
                .title("query")
                .text("received")
                .ticketDateTime(LocalDateTime.now())
                .build();
        TicketBl.getTicketBl().save(ticket);        //TODO
        return ticket;
    }

    @PUT
    public Ticket edit(
            @QueryParam("id") int id,
            @QueryParam("group") String group,
            @QueryParam("personId") int personId,
            @QueryParam("title") String title,
            @QueryParam("text") String text,
            @QueryParam("ticketDateTime") LocalDateTime ticketDateTime) {         //TODO
        return Ticket
                .builder()
                .id(1)
                .group(Group.Materiel)
                .person(Person.builder().id().build())          //TODO
                .title("query")
                .text("received")
                .ticketDateTime(LocalDateTime.now())
                .build();
        TicketBl.getTicketBl().edit();      //TODO
    }

    @GET
    public String find() {          // TODO : EVERYWHERE??
        return "Find";
    }

    @GET
    @Path("/all")
    public String findAll() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        TicketBl.getTicketBl().findAll();
        ticketList.add(ticket);             // TODO
        Gson gson = new Gson();
        return gson.toJson(ticketList);
//        Ticket ticket = Ticket
//                .builder()
//                .id(1)
//                           .person(Person.builder().id(getInt("PERSON_ID"))build())
//                .ticketDateTime(LocalDateTime.now())
//                .group(Group.Materiel)
//                .title("Query")
//                .text("welcome")
//                .build();
//        TicketBl.getTicketBl().findAll();
//        List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(ticket);
    }

    @Path("/all/{id}")
    public String findById(@PathParam("id") int id) throws Exception {          //TODO : TICKET | INT | STRING ?
        Ticket ticket = Ticket
                .builder()
                .id(1)
                //.person(Person.builder().id(getInt("PERSON_ID"))build())
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findAll();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticketList.get(id));
    }

    @Path("/all/{personId}")            //TODO  :   personId?
    public String findByPersonId(@PathParam("personId") int id) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID")).build())          //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByPersonId(id);
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticketList);
    }

    @Path("/all/{group}")
    public String findByGroup(@PathParam("group") Group group) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID"))build())        //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByGroup(group);          //TODO FOR GROUP TYPE??
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticketList.get(Group.valueOf()));           //TODO
    }

    @Path("/all/{title}")
    public String findByTitle(@PathParam("title") String title) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID"))build())        //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByTitle(title);
        //     List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticket.getTitle());
    }

    @Path("/all/{text}")
    public String findByText(@PathParam("text") String text) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID"))build())        //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByText(text);
        //     List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticket.getText());
    }
    @Path("/all/{dateRange}")
    public String findByDateRange(@PathParam("dateRange") LocalDateTime startDateTime, LocalDateTime endDateTime) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID"))build())        //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByDateRange(startDateTime,endDateTime);                       //  TODO
        //     List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticket.getTicketDateTime(startDateTime,endDateTime);         // TODO
}
    @Path("/all/{username}")
    public String findByUsername(@PathParam("username") String username) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                .person(Person.builder().id(getInt("PERSON_ID"))build())          //TODO
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findByUsername(username);
//        List<Ticket> ticketList = new ArrayList<>();
//        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticket.getPerson().getUsername());               //TODO
}
}
