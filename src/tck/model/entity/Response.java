package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@SuperBuilder
@Setter
@Getter

public class Response {
    private int id;
    private Ticket ticket;
    private Person person;
    private String answer;
    private LocalDate date;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

