package tck.controller;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

import java.util.List;

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

    public static void findAll(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            List<Person> personList = PersonBl.getPersonBl().findAll();
            System.out.println("info:person found:");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void findById(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            PersonBl.getPersonBl().findById(person.getId());                    //TODO THIS ONE OR THE DOWN LINE
//      |  PersonBl.getPersonBl().findById(id);
            System.out.println("person found by id:");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void findByFamily(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            List<Person> personList = PersonBl.getPersonBl().findByFamily(family);          //TODO
            System.out.println("person found by family:");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void findByUsername(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            PersonBl.getPersonBl().findByUsername(username);
            System.out.println("person found by username:");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void findByUsernameAndPassword(int id, String name, String family, String phoneNumber, String email, String username, String password, Role role, Boolean enabled) {
        try {
            Person person = Person.builder().id(id).name(name).family(family).phoneNumber(phoneNumber).email(email).username(username).password(password).role(Role.valueOf(("Role"))).enabled(getBoolean("enabled")).build();
            PersonBl.getPersonBl().findByUsernameAndPassword(username, password);
            System.out.println("person found by Username Aand Password:");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
