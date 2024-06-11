package tck.controller;

import tck.model.bl.ResponseBl;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

//public class ResponseController {
//    public static void save(Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = new Response();
//            response.setPerson(person).setTicket(ticket).setAnswer(answer).setStatus(status).setLocalDateTime(dateTime);            //TODO
//            ResponseBl.getResponseBl().save(response);
//            System.out.println("response saved.");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static void edit(int id, Person person, Ticket ticket, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();            //TODO FOR DATE   &   SetId!
 //            TODO for SET??
//            ResponseBl.getResponseBl().edit(response);
//            System.out.println("response updated!.");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findAll(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            List<Response> responseList = ResponseBl.getResponseBl().findAll();           //TODO
//            System.out.println("info:response found");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findById(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findById(id);
//            System.out.println("info:response found by id");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findByPersonId(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findByPersonId(id);
//            System.out.println("info:response found by person id");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findByTicketId(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findByTicketId(id);
//            System.out.println("info:response found by ticket id");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findByAnswer(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findByAnswer(answer);
//            System.out.println("info:response found by answer");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findByDateTime(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findByDateTime(dateTime);        // TODO : CHECK!!!
//            System.out.println("info:response found by date time");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//
//    public static void findByStatus(int id, Ticket ticket, Person person, String answer, Status status, LocalDateTime dateTime) {
//        try {
//            Response response = Response.builder().id(id).status(Status.valueOf("status")).person(person.setId(id)).ticket(ticket.setId(id)).answer(answer).dateTime(LocalDateTime.now()).build();
//            ResponseBl.getResponseBl().findByStatus(status);
//            System.out.println("info:response found by status");
//        } catch (Exception e) {
//            System.out.println("Error" + e.getMessage());
//        }
//    }
//}
//