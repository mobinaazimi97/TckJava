package tck.controller;

import com.google.gson.Gson;
import tck.model.bl.PersonBl;
import tck.model.da.PersonDa;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("   /  person")
public class PersonController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson() {
        Person person = Person
                .builder()
                .id(1)
                .name("mobi")
                .family("azX")
                .phoneNumber("09123234334")
                .email("www.azx.com")
                .username("mobi123")
                .password("12345")
                .role(Role.Admin)
                .enabled(true)
                .build();
        return Response.status(201).entity(person).build();
    }

    @POST
    public Person save(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("phoneNumber") String phoneNumber,
            @QueryParam("email") String email,
            @QueryParam("username") String username,
            @QueryParam("password") String password,
            @QueryParam("role") Role role,
            @QueryParam("enabled") Boolean enabled) {
        PersonBl.getPersonBl().save();
        return Person
                .builder()
                .id(1)
                .name("mobi")
                .family("azX")
                .phoneNumber("09123234334")
                .email("www.azx.com")
                .username("mobi123")
                .password("12345")
                .role(Role.Admin)
                .enabled(true)
                .build();
    }
    public String findAall() throws SQLException {
        PersonDa personDa=new PersonDa();
        List<Person> personList=new ArrayList<>();
        personList.add(personDa.findAll())
    }
}
