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
    }

    @Override
    public Person save(Person person) throws Exception {
        person.setId(ConnectionProvider.getConnectionProvider().getNextId("ticket_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON(ID,PERSON_NAME,PERSON_FAMILY,Email,Phone_Number,USER_NAME,PASSWORD,ROLE,ENABLED) VALUES (PERSON_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
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
                "UPDATE PERSON SET PERSON_NAME=?,PERSON_FAMILY=?,PHONE_NUMBER=?,EMAIL=?,USER_NAME=?,ROLE=?,PASSWORD=?,ENABLED=? WHERE id=?");

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
                "DELETE FROM PERSON WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Person> findAll() throws Exception {
        List<Person> personList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from person order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
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
        preparedStatement = connection.prepareStatement("select * from person where family LIKE ? order by id");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Person person = Person
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
                    .username(resultSet.getString("userName"))
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
        preparedStatement = connection.prepareStatement("select * from person where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
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
        preparedStatement = connection.prepareStatement("SELECT * FROM  PERSON WHERE USER_NAME=?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
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
        preparedStatement = connection.prepareStatement("SELECT * FROM  PERSON WHERE USER_NAME=? AND PASSWORD=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person = Person
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
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
    }
}
