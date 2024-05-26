package tck.model.entity;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Person {
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private String gmail;
    private String userName;
    private String passWord;
    @Override
    public String toString(){
        return new Gson().toJson(this);
    }


}
