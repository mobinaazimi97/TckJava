package tck.model.entity;

import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Group;
import java.time.LocalDateTime;

@NoArgsConstructor
@SuperBuilder

public class Ticket {
    private int id;
    private Group group;
    private Person person;
    private String title;
    private String text;
    private LocalDateTime ticketDateTime;
// private Status status;                                           //TODO


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

    public Ticket setGroup(Group group) {
        this.group = group;
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

    public Ticket setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDateTime getTicketDateTime() {
        return ticketDateTime;
    }

    public Ticket setTicketDateTime(LocalDateTime ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
        return this;
    }
}

