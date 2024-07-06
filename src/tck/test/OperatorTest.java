package tck.test;

import tck.model.bl.*;
import tck.model.entity.*;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;
import java.time.LocalDate;

public class OperatorTest {
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
        Response response=
                Response.builder()
                        .id(1)
                        .ticket(Ticket.builder().id(1).build())
                        .person(Person.builder().id(1).build())
                        .answer("hello")
                        .date(LocalDate.now())
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
        Admin admin = Admin
                .builder()
                .id(1)
                .user("mobina")
                .pass("1234567")
                .person(person)
                .ticket(ticket)
                .response(response)
                .build();
        SignIn signIn=SignIn
                .builder()
                .id(1)
                .person(person)
                .admin(admin)
                .build();
        Operator operator=Operator
                .builder()
                .id(1)
                .operateNumber("123456")
                .signIn(signIn)
                .admin(admin)
                .signIn(signIn)
                .build();
        PersonBl.getPersonBl().save(person);
//        System.out.println(person);
        TicketBl.getTicketBl().save(ticket);
  //      System.out.println(ticket);
        ResponseBl.getResponseBl().save(response);
  //      System.out.println(response);
        AdminBl.getAdminBl().save(admin);
 //       System.out.println(admin);
        SignInBl.getSignInBl().save(signIn);
//        System.out.println(signIn);

        OperateBl.getOperateBl().save(operator);
        System.out.println(operator);
        System.out.println("find all: "+ OperateBl.getOperateBl().findAll());

    }
}
