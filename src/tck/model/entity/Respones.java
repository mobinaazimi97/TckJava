package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Respones {
    private int id;
    private Ticket ticket;
    private Person person;
    private LocalDateTime dateTime;
    private Status status;
 private String answer;
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

