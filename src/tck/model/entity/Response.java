package tck.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
    public Response setTicket(Ticket ticket) {
        this.ticket = ticket;

    }

    public Person getPerson() {
        return person;
    }

    public Response setPerson(Person person) {
        this.person = person;
    }

    public String getAnswer() {
        return answer;
    }

    public Response setAnswer(String answer) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{10,255}$",answer)) {
            this.answer = answer;
        }else{
            throw new Exception("Invalid Answer");
        }
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Response setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}

