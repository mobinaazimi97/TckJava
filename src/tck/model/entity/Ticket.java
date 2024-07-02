package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;

import java.time.LocalDate;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter

public  class Ticket {
    private int id;
    private Group group;
    private Person person;
    private String title;
    private String text;
    private LocalDate ticketDate;
    private Status status;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

