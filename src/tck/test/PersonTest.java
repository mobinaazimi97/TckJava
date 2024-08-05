package tck.test;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

public class PersonTest {
    public static void main(String[] args) throws Exception {
        Person person =
                Person.builder()
                        .id(1)
                        .name("mobina")
                        .family("azimi")
                        .phoneNumber("091943346556")
                        .email("www.mobina.com")
                        .username("mobi123")
                        .password("1234")
                        .role(Role.valueOf(Role.Admin.name()))
                        .enabled(true)
                        .build();
        PersonBl.getPersonBl().save(person);
        System.out.println(person);

        System.out.println(PersonBl.getPersonBl().findByUsername("mobi123"));
        System.out.println(PersonBl.getPersonBl().findById(1));
   //     PersonBl.getPersonBl().findById(person.getId());
    }
}
