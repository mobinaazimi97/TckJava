package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.Admin;
import tck.model.entity.Person;
import tck.model.entity.SignIn;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class SignInDa implements AutoCloseable, CRUD<SignIn> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public SignInDa()throws Exception{
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");
    }

    @Override
    public SignIn save(SignIn signIn) throws Exception {
        signIn.setId(ConnectionProvider.getConnectionProvider().getNextId("SignIn_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO SIGNIN(sign_id,person_id,user_name,password,email,phone_number,admin_id) VALUES (signIn_seq.NextVal,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1,signIn.getId());
        preparedStatement.setInt(2,signIn.getPerson().getId());
        preparedStatement.setString(3,signIn.getPerson().getUsername());
        preparedStatement.setString(4,signIn.getPerson().getPassword());
        preparedStatement.setString(5,signIn.getPerson().getEmail());
        preparedStatement.setString(6,signIn.getPerson().getPhoneNumber());
        preparedStatement.setInt(7,signIn.getAdmin().getId());

        return signIn;
    }

    @Override
    public SignIn edit(SignIn signIn) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE SIGNIN SET sign_id=?,person_id=?,user_name=?,password=?,email=?,phone_number=?,admin_id=? WHERE Sign_Id=?");
        preparedStatement.setInt(1,signIn.getId());
        preparedStatement.setInt(2,signIn.getPerson().getId());
        preparedStatement.setString(3,signIn.getPerson().getUsername());
        preparedStatement.setString(4,signIn.getPerson().getPassword());
        preparedStatement.setString(5,signIn.getPerson().getEmail());
        preparedStatement.setString(6,signIn.getPerson().getPhoneNumber());
        preparedStatement.setInt(7,signIn.getAdmin().getId());
        return signIn;
    }

    @Override
    public SignIn remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM SIGNIN WHERE SIGN_ID= ?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<SignIn> findAll() throws Exception {
        List<SignIn> signInList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from SIGNIN order by PERSON_ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            SignIn signIn=SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
            signInList.add(signIn);
        }
        return signInList;
    }

    @Override
    public SignIn findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Sign_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn= null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByPersonId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Person_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByPersonUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where User_name LIKE? ORDER BY SIGN_ID");
        preparedStatement.setString(1, username + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                   .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
//                    .person(Person.builder().password(resultSet.getString("password")).build())               TODO
//                    .person(Person.builder().email(resultSet.getString("email")).build())
//                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByPersonPassword(String password) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Password LIKE? ORDER BY SIGN_ID");
        preparedStatement.setString(1, password + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                   .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByPersonEmail(String email) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Email LIKE? ORDER BY SIGN_ID");
        preparedStatement.setString(1, email + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                   .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                   .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByPersonPhoneNumber(String phoneNumber) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Phone_Number LIKE? ORDER BY SIGN_ID");
        preparedStatement.setString(1, phoneNumber + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }
    public SignIn findByAdminId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from SignIn where Admin_Id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        SignIn signIn = null;
        if (resultSet.next()) {
            signIn = SignIn
                    .builder()
                    .id(resultSet.getInt("sign_id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().email(resultSet.getString("email")).build())
                    .person(Person.builder().phoneNumber(resultSet.getString("phone_number")).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .build();
        }
        return signIn;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From Database ");
    }
}
