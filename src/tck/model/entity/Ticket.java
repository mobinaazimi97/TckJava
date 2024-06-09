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

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
  //      person.toBuilder()                //TODO
        this.person = person;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTicketDateTime() {
        return ticketDateTime;
    }

    public void setTicketDateTime(LocalDateTime ticketDateTime) {
        this.ticketDateTime = ticketDateTime;
    }
}

