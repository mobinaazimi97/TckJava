package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.DuplicateUsernameException;
import tck.controller.exceptions.NoPersonFoundException;
import tck.model.da.PersonDa;
import tck.model.entity.Person;
import tck.model.tool.CRUD;

import java.util.List;

public class PersonBl implements CRUD<Person> {
    @Getter
    private static PersonBl personBl = new PersonBl();

    private PersonBl() {
    }

    @Override
    public Person save(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            if (personDa.findByUsername(person.getUsername())==null){
                personDa.save(person);
            }else {
                throw new DuplicateUsernameException();
            }
//            personDa.save(person);
            return person;
        }
    }


    @Override
    public Person edit(Person person) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            if (personDa.findById(person.getId()) != null) {
                personDa.edit(person);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Person remove(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findById(id);
            if (person != null) {
                personDa.remove(id);
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public List<Person> findAll() throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Person> personList = personDa.findAll();
            if (!personList.isEmpty()) {
                return personList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    @Override
    public Person findById(int id) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findById(id);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public List<Person> findByFamily(String family) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            List<Person> personList = personDa.findByFamily(family);
            if (!personList.isEmpty()) {
                return personList;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public Person findByUsername(String username) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findByUsername(username);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }

    public Person findByPassword(String password) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findByPassword(password);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }
    public Person findByPhoneNumber(String phoneNumber) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findByPhoneNumber(phoneNumber);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }
    public Person findByEmail(String email) throws Exception {
        try (PersonDa personDa = new PersonDa()) {
            Person person = personDa.findByEmail(email);
            if (person != null) {
                return person;
            } else {
                throw new NoPersonFoundException();
            }
        }
    }
}
