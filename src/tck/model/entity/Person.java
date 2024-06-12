package tck.model.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tck.model.entity.enums.Role;

import java.io.Serializable;
import java.util.regex.Pattern;

@NoArgsConstructor
@SuperBuilder(toBuilder = true)


public class Person implements Serializable {
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private Role role;
    private boolean enabled;


    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",name)) {
            this.name = name;
        }else{
            throw new Exception("Invalid Name");
        }
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) throws Exception {
        if(Pattern.matches("^[a-zA-Z\\s]{2,30}$",family)) {
            this.family  = family;
        }else{
            throw new Exception("Invalid Family");
        }
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Person setPhoneNumber(String phoneNumber) throws Exception {
      if(Pattern.matches("^[0-9]{10,13}$",phoneNumber)) {                                                    //TODO : CHECK!!!
  //      if(Pattern.matches("^[\\d]{10} | [\\d]{3}-[\\d]{6}- [\\d]$",phoneNumber)){                            //TODO : [ ]
            this.phoneNumber  = phoneNumber;
        }else{
            throw new Exception("Invalid PhoneNumber");
        }
        return this;
    }
      public String getEmail() {
        return email;
    }
      public Person setEmail(String email) throws Exception {
        if(Pattern.matches("^[a-zA-Z0-9]{20,50}$",email)) {
            this.email = email;
        }else{
            throw new Exception("Invalid Email");
        }
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Person setUsername(String username) throws Exception {
        if(Pattern.matches("^[a-zA-Z0-9\\s]{5,20}$",username)) {
            this.username  = username;
        }else{
            throw new Exception("Invalid username");
        }
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) throws Exception {
        if(Pattern.matches("^[a-zA-Z0-9]{6,10}$",password)) {
            this.password = password;
        }else{
            throw new Exception("Invalid Password");
        }
        return this;
    }

    public Role getRole() {
        return role;
    }

  public Person setRole(Role role) throws Exception {
//        if(Pattern.matches("^[a-zA-Z]{5}$",Role.valueOf(String.valueOf(role))) {
//            this.role  = Role.valueOf(String.valueOf(role));
//        }else{
//            throw new Exception("Invalid Role");
//        }
      return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Person setEnabled(boolean enabled) {

        return this;
    }
}
