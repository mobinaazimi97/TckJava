package tck.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Role;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)


public class Person {
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private Role role;
    private boolean enabled;


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
