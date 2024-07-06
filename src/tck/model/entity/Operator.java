package tck.model.entity;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Operator {
    private int id;
    private String operateNumber;
    private SignIn person;
    private Admin admin;
    private SignIn signIn;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
