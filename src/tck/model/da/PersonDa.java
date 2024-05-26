package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.Person;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Log4j
    public class PersonDa implements AutoCloseable , CRUD<Person> {
        private final Connection connection;
        private PreparedStatement preparedStatement;
        public PersonDa() throws SQLException {
            connection = ConnectionProvider.getConnectionProvider().getConnection();
        }
        @Override
        public Person save(Person person) throws Exception {
            person.setId(ConnectionProvider.getConnectionProvider().getNextId("person14_seq"));
            preparedStatement=connection.prepareStatement(
                    "INSERT INTO PERSON14(ID,NAME,FAMILY,Gmail,Phone_Number,USER_NAME,PASSWORD,ADDMIN) VALUES (PERSON14_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,)"
            );
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getFamily());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.setString(4, person.getPhoneNumber());
            preparedStatement.setString(5,person.getAdminName());
            preparedStatement.setString(6,person.getUserName());
            preparedStatement.setString(7,person.getPassWord());
            preparedStatement.execute();
            return person;
        }
        @Override
        public Person edit(Person person) throws Exception {
            preparedStatement=connection.prepareStatement(
                    "UPDATE PERSON14 SET NAME=?,FAMILY=?,PHONE_NUMBER=?,GMAIL=?,USER_NAME=?,PASSWORD=?,ADDMIN=?WHERE id=?");

            preparedStatement.setString( 1,person.getName());
            preparedStatement.setString( 2,person.getFamily());
            preparedStatement.setString( 3,person.getGmail());
            preparedStatement.setString(   4,person.getPhoneNumber());
            preparedStatement.setString( 5,person.getUserName());
            preparedStatement.setString(6,person.getPassWord());
            preparedStatement.setString(7,person.getAdminName());
            preparedStatement.setInt(    8,person.getId());
            preparedStatement.execute();
            return person;
        }
        @Override
        public Person remove(int id) throws Exception {
            preparedStatement=connection.prepareStatement(
                    "DELETE FROM PERSON14 WHERE ID = ?"
            );
            preparedStatement.setInt(1 , id);
            preparedStatement.execute();
            return null;
        }
        @Override
        public List<Person> findAll() throws Exception {
            List<Person>personList = new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from person14 order by id");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Person person = Person
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .family(resultSet.getString("family"))
                        .phoneNumber(resultSet.getString("Phone_Number"))
                        .gmail(resultSet.getString("Gmail"))
                        .userName(resultSet.getString("USER_NAME"))
                        .passWord(resultSet.getString("Password"))
                        .adminName(resultSet.getString("Admin"))
                        .build();

                personList.add(person);
            }
            return personList;

        }
        public List<Person> findByFamily(String family) throws Exception {
            List<Person>personList = new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from person14 where family LIKE ? order by id");
            preparedStatement.setString(1,family+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Person person = Person
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .family(resultSet.getString("family"))
                        .phoneNumber(resultSet.getString("phoneNumber"))
                        .gmail(resultSet.getString("Gmail"))
                        .userName(resultSet.getString("userName"))
                        .passWord (resultSet.getString("passWord"))
                        .adminName(resultSet.getString("addmin"))
                        .build();

                personList.add(person);
            }
            return personList;

        }
        @Override
        public Person findById(int id) throws Exception {
            preparedStatement = connection.prepareStatement("select * from person14 where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            Person person = null;
            if(resultSet.next()){
                person = Person
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .family(resultSet.getString("family"))
                        .phoneNumber(resultSet.getString("Phone_Number"))
                        .gmail(resultSet.getString("Gmail"))
                        .userName(resultSet.getString("User_Name"))
                        .passWord(resultSet.getString("Password"))
                        .adminName(resultSet.getString("admin"))
                        .build();
            }
            return person;

        }

        @Override
        public void close()throws  Exception {
            preparedStatement.close();
            connection.close();
        }
}
