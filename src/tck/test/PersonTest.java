package tck.test;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

public class PersonTest {
    public static void main(String[] args) throws Exception {
        System.out.println(PersonBl.getPersonBl().save(
                Person.builder()
                        .id(1)
                        .name("ali")
                        .family("alipour")
                        .phoneNumber("09121211312")
                        .email("www.alipour.com")
                        .username("ali")
                        .password("ali123")
                        .role(Role.Admin)
                        .build()
        ));
    }
}
