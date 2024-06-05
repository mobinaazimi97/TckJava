package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Status;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Response {
    private int id;
    private Ticket ticket;
    private Person person;
    private String answer;
    private LocalDateTime dateTime;
    private Status status;                          //TODO

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

