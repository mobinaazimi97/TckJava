package tck.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@NoArgsConstructor
@SuperBuilder

public class Ticket {
    private int id;
    private Group group;
    private Person person;
    private String title;
    private String text;
    private LocalDateTime ticketDateTime;
    private Status status;


    public int getId() {
        return id;
    }

    public Ticket setId(int id) {
        this.id = id;
        return this;
    }

    public Group getGroup() {
        return group;
    }

    public Ticket setGroup(Group group) throws Exception {
        if(Pattern.matches("^[a-zA-Z]{}$",group)) {
            this.group= group;
        }else{
            throw new Exception("Invalid Answer");
        }
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public Ticket setPerson(Person person) {
        this.person = person;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Ticket setTitle(String title) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{}$",title)) {
            this.title = title;
        }else{
            throw new Exception("Invalid Answer");
        }
        return this;
    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{}$",text)) {
            this.text = text;
        }else{
            throw new Exception("Invalid Answer");
        }
        return this;
    }

    public LocalDateTime getTicketDateTime() {
        return ticketDateTime;
    }

    public Ticket setTicketDateTime(LocalDateTime ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
        return this;
    }
    public Status getStatus() {
        return status;
    }

    public Ticket setStatus(Status status) throws Exception {
        if(Pattern.matches("^[a-zA-Z]{}$",group)) {
            this.group = group;
        }else{
            throw new Exception("Invalid Answer");
        }
        return this;
    }
}

