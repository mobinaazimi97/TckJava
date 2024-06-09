package tck;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

public class Main1 {
    public static void main(String[] args) throws Exception {
        PersonBl.getPersonBl().save(Person
                .builder()
                .id(1)
                .name("mobina")
                .family("azimi")
                .phoneNumber("0912232324")
                .email("www.azimi.ir")
                .username("mobi123")
                .password("123456")
                .role(Role.Admin)
                .enabled(true)
                .build()
        );
        System.out.println("person saved");

    }
}
