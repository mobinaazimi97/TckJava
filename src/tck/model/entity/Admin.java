package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter

public class Admin {
    private int id;
    private String user;
    private String pass;
    private Person person;
    private Ticket ticket;
    private Response response;
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
