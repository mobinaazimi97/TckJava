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

public class SignIn {
    private int id;
    private Person person;
    private Admin admin;
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
