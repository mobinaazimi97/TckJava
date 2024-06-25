package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.Person;
import tck.model.entity.enums.Role;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class PersonDa implements AutoCloseable, CRUD<Person> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");
    }

    @Override
    public Person save(Person person) throws Exception {
        person.setId(ConnectionProvider.getConnectionProvider().getNextId("person_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON(PERSON_NAME,PERSON_FAMILY,Email,Phone_Number,USER_NAME,PASSWORD,ROLE,ENABLED) VALUES (?,?,?,?,?,?,?,?,?)"
                                                                                                                                                                                                                                                     //RESPONSE_SEQ.NEXTVAL,?..TODO
        );
   //     preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getPhoneNumber());
        preparedStatement.setString(6, person.getUsername());
        preparedStatement.setString(7, person.getPassword());
        preparedStatement.setString(8, String.valueOf(person.getRole()));
        preparedStatement.setBoolean(9, person.isEnabled());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person edit(Person person) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET PERSON_NAME=?,PERSON_FAMILY=?,PHONE_NUMBER=?,EMAIL=?,USER_NAME=?,ROLE=?,PASSWORD=?,ENABLED=? WHERE person_id=?");

        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getPhoneNumber());
        preparedStatement.setString(4, person.getEmail());
        preparedStatement.setString(5, person.getUsername());
        preparedStatement.setString(6, String.valueOf(person.getRole()));
        preparedStatement.setString(7, person.getPassword());
        preparedStatement.setInt(8, person.getId());
        preparedStatement.setBoolean(9, person.isEnabled());
        preparedStatement.execute();
        return person;
    }

    @Override
    public Person remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON WHERE person_ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from person order by person_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("person_id"))
                    .name(resultSet.getString("person_name"))
                    .family(resultSet.getString("person_family"))
                    .phoneNumber(resultSet.getString("Phone_Number"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("USER_NAME"))
                    .password(resultSet.getString("Password"))
                    .role(Role.valueOf(resultSet.getString("Role")))
                    .enabled(resultSet.getBoolean("ENABLED"))
                    .build();

            personList.add(person);
        }
        return personList;
    }

    public List<Person> findByFamily(String family) throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from person where person_family LIKE ? order by person_id");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("person_id"))
                    .name(resultSet.getString("person_name"))
                    .family(resultSet.getString("person_family"))
                    .phoneNumber(resultSet.getString("phone_Number"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("user_Name"))
                    .password(resultSet.getString("passWord"))
                    .role(Role.valueOf(resultSet.getString("Role")))
                    .enabled(resultSet.getBoolean("ENABLED"))
                    .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from person where person_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("person_id"))
                    .name(resultSet.getString("person_name"))
                    .family(resultSet.getString("person_family"))
                    .phoneNumber(resultSet.getString("Phone_Number"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("User_Name"))
                    .password(resultSet.getString("Password"))
                    .role(Role.valueOf(resultSet.getString("Role")))
                    .enabled(resultSet.getBoolean("ENABLED"))
                    .build();
        }
        return person;
    }

    public Person findByUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM  PERSON WHERE USER_NAME LIKE? ORDER BY ID");
        preparedStatement.setString(1, username + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("PERSON_NAME"))
                    .family(resultSet.getString("PERSON_FAMILY"))
                    .phoneNumber(resultSet.getString("Phone_Number"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("User_Name"))
                    .password(resultSet.getString("Password"))
                    .role(Role.valueOf(resultSet.getString("Role")))
                    .enabled(resultSet.getBoolean("ENABLED"))
                    .build();
        }
        return person;
    }

    public Person findByUsernameAndPassword(String username, String password) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM  PERSON WHERE USER_NAME LIKE? AND PASSWORD LIKE? ORDER BY ID");
        preparedStatement.setString(1, username + "%");
        preparedStatement.setString(2, password + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("PERSON_ID"))
                    .name(resultSet.getString("PERSON_NAME"))
                    .family(resultSet.getString("PERSON_FAMILY"))
                    .phoneNumber(resultSet.getString("Phone_Number"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("User_Name"))
                    .password(resultSet.getString("Password"))
                    .role(Role.valueOf(resultSet.getString("Role")))
                    .enabled(resultSet.getBoolean("ENABLED"))
                    .build();
        }
        return person;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From Database ");
    }
}
