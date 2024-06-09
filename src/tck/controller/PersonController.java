package tck.controller;

import com.google.gson.Gson;
import tck.model.bl.PersonBl;
import tck.model.da.PersonDa;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            @QueryParam("enabled") Boolean enabled) throws Exception {              //TODO
  //          PersonBl.getPersonBl().save(Person.builder().build());                        //TODO
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

    @PUT
    public String edit(
                        @QueryParam("id") int id,
                       @QueryParam("name") String name,
                       @QueryParam("family") String family,
                       @QueryParam("phoneNumber") String phoneNumber,
                       @QueryParam("email") String email,
                       @QueryParam("username") String username,
                       @QueryParam("password") String password,
                       @QueryParam("role") Role role,
                       @QueryParam("enabled") Boolean enabled) {
 // TODO :  PersonBl.getPersonBl().edit(person);                            //TODO
        Person person = new Person();
        Gson gson = new Gson();                     //TODO : GSON? EVERYWHERE?
        return gson.toJson(person);

    }
    @GET
    @Path("/all")
    public String findAll() throws Exception {
        List<Person> personList=PersonBl.getPersonBl().findAll();                   //TODO
        Gson gson = new Gson();
        return gson.toJson(personList);
    }
    @GET
    @Path("/all/{family}")
    public String findByFamily(@PathParam("family") String family) throws Exception {
        List<Person> personList = PersonBl.getPersonBl().findByFamily(family);
        Gson gson = new Gson();
        return gson.toJson(personList);
    }
    @GET
    @Path("/all/{id}")
    public String findById(@PathParam("id") int id) throws Exception {                   //TODO : THROWS EXCEPTION??
        PersonBl.getPersonBl().findById(id);
        Person person = new Person();
 //       List<Person> personList = new ArrayList<>();
//     personList.add();                                                                  //TODO
       Gson gson = new Gson();
     return gson.toJson(person.getId());
    }
    @GET
    @Path("/all/{username}")
    public String findByUsername(@PathParam("username") String username) throws Exception {                   // TODO
        PersonBl.getPersonBl().findByUsername(username);
        Person person = new Person();
        Gson gson = new Gson();
        return gson.toJson(person.getUsername());
//        return username + " Panel";
    }
    @GET
    @Path("/password/username/")
  public String findByUsernameAndPassword(@PathParam("password,username") String username , String password) throws Exception {       //TODO
        PersonBl.getPersonBl().findByUsernameAndPassword(username, password);
        Person person = new Person();
        Gson gson = new Gson();
        return gson.toJson(person);
    }
}

//    public String findAall() throws SQLException {
   //     PersonDa personDa = new PersonDa();
//        List<Person> personList=new ArrayList<>();
        //       personList.add(personDa.findAll())
        //   }
 //   }

