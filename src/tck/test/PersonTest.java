package tck.test;

import tck.model.bl.PersonBl;
import tck.model.entity.Person;

import java.time.LocalDate;

public class PersonTest {
    public static void main(String[] args) {
    //        System.out.println(PersonBl.getPersonBl().findAll());
//        System.out.println(PersonBl.getPersonBl().findById(1));
//        System.out.println(PersonBl.getPersonBl().findByFamily("alipour"));
//        System.out.println(PersonBl.getPersonBl().remove(1));
       System.out.println(PersonBl.getPersonBl(). edit(
            Person.builder()
                             .id(1)

                         .name("ali")

                      .family("alipour")

                    .gender(Gender.Female)

                    .city(City.Tabriz)

                .birthDate(LocalDate.now())
                            .build()
       ));
}
}
