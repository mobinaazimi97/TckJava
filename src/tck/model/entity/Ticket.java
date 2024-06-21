package tck.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.time.LocalDate;
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
    private LocalDate ticketDate;
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
//TODO        if(Pattern.matches("^[a-zA-Z]{5,20}$",group)) {
//            this.group= group;
//        }else{
//            throw new Exception("Invalid Group");
//        }
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
        if(Pattern.matches("^[a-zA-Z\\s]{5,20}$",title)) {
            this.title = title;
        }else{
            throw new Exception("Invalid Title");
        }
        return this;
    }

    public String getText() {
        return text;
    }

    public Ticket setText(String text) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{10,255}$",text)) {
            this.text = text;
        }else{
            throw new Exception("Invalid Text");
        }
        return this;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public Ticket setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
        return this;
    }
    public Status getStatus() {
        return status;
    }

    public Ticket setStatus(Status status) throws Exception {
//TODO        if(Pattern.matches("^[a-zA-Z]{6}$",status)) {
//            this.group = status;
//        }else{
//            throw new Exception("Invalid Status");
//        }
        return this;
    }
}

