package tck.controller;


import com.google.gson.Gson;
import org.omg.CORBA.PUBLIC_MEMBER;
import tck.model.bl.TicketBl;
import tck.model.entity.Person;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/Ticket")
public class TicketController {
    @GET
    public String find(){
        return "Find";
    }
    @GET
    @Path("/all")
    public String findAll() throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
 //            .person(Person.builder().id(getInt("PERSON_ID"))build())
                .ticketDateTime(LocalDateTime.now())
                .group(Group.Materiel)
                .title("Query")
                .text("welcome")
                .build();
        TicketBl.getTicketBl().findAll();
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket);

        Gson gson = new Gson();
        return gson.toJson(ticketList);
    }
    @Path("/all/{id}")
    public String findAll(@PathParam("id") int id) throws Exception {
        Ticket ticket = Ticket
                .builder()
                .id(1)
                //            .person(Person.builder().id(getInt("PERSON_ID"))build())
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
    }
