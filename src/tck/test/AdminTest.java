package tck.test;

import tck.model.bl.AdminBl;
import tck.model.bl.PersonBl;
import tck.model.entity.Admin;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;

import java.time.LocalDate;

public class AdminTest {
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
                        .role(Role.Admin)
                        .enabled(true)
                        .build();
        Ticket ticket=
                Ticket.builder()
                        .id(1)
                        .group(Group.Materiel)
                        .status(Status.answer)
                        .ticketDate(LocalDate.now())
                        .text("answered")
                        .title("requiery")
                        .person(person)
                        .build();
        Response response=
                Response.builder()
                        .id(1)
                        .ticket(Ticket.builder().id(1).build())
                        .person(Person.builder().id(1).build())
                        .answer("hello")
                        .date(LocalDate.now())
                        .build();
        Admin admin = Admin
                .builder()
                .id(1)
                .user("mobina")
                .pass("1234567")
                .person(person)
                .ticket(ticket)
                .response(response)
                .build();
        System.out.println(AdminBl.getAdminBl().save(admin));
        System.out.println(PersonBl.getPersonBl().save(person));
        System.out.println(AdminBl.getAdminBl().findByPersonId(admin.getPerson().getId()));
 //       System.out.println(AdminBl.getAdminBl().findByPass("1234567"));
 //       System.out.println(AdminBl.getAdminBl().findById(admin.getId()));
//        System.out.println(admin);
//        System.out.println(admin.getTicket()); //todo
        System.out.println(admin.getPerson().getRole());
//        System.out.println(AdminBl.getAdminBl().findByPass(admin.getPass()));
     //   System.out.println(AdminBl.getAdminBl().findById(admin.getId()));
   //     System.out.println(AdminBl.getAdminBl().findAll());
    }
}
