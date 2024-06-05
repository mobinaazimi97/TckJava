package tck.controller;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

import static java.lang.Boolean.getBoolean;

public class PController {
    public static void save(String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = new Person();
            person.setName(name).setFamily(family).setPhoneNumber(phoneNumber).setEmail(email).setUsername(username).setPassword(password).setRole(role);
            PersonBl.getPersonBl().save(person);
            System.out.println("person saved.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void edit(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            System.out.println("Person Edited");
            // Data Validation
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            PersonBl.getPersonBl().edit(person);
            System.out.println("Info : Person Edited");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
