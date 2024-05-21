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
public class Ticket {
    private int id;
    private LocalDateTime ticketDateTime;
    private Person person;
    private String title;
    private Group group;
    private Status status;
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

}
