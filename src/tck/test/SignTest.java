package tck.test;

import tck.model.bl.SignInBl;
import tck.model.entity.*;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Role;
import tck.model.entity.enums.Status;

import java.time.LocalDate;

public class SignTest {
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
        SignInBl.getSignInBl().save(signIn);
        System.out.println(signIn);
    }
}
