package tck.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@NoArgsConstructor
@SuperBuilder

public class Response {
    private int id;
    private Ticket ticket;
    private Person person;
    private String answer;
    private LocalDateTime dateTime;

    public int getId() {
        return id;
    }

    public Response setId(int id) {
        this.id = id;
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Response setTicket(Ticket ticket) throws Exception {
//   todo     Response response=Response.builder()
//                .id(id)
//                .ticket(Ticket.builder().id(id).build())
//                .person(Person.builder().id(id).build())
//                .dateTime(LocalDate.now())
//                .answer(answer)
//                .build();
//        if(Pattern.matches("^[0-9]{1,1000}$",ticket) {
//            this.ticket=ticket;
//        }else{
//            throw new Exception("Invalid Ticket Id");
        //      }
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Response setPerson(Person person) throws Exception {
//  TODO      Response response=Response.builder().id(id).person(Person.builder().id(id).build()).ticket(Ticket.builder().id(id).build()).dateTime(LocalDate.now()).answer(answer).build();
//        if(Pattern.matches("^[0-9]{1,1000}$",person.setId())) {             //TODO
//            this.person=person;
//        }else{
//            throw new Exception("Invalid Person id");
//        }
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Response setAnswer(String answer) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{10,255}$", answer)) {
            this.answer = answer;
        } else {
            throw new Exception("Invalid Answer");
        }
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Response setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

}

