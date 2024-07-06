package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.*;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j

public class OperateDa implements AutoCloseable,CRUD<Operator> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public OperateDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");
    }
    @Override
    public Operator save(Operator operator) throws Exception {
        operator.setId(ConnectionProvider.getConnectionProvider().getNextId("operator_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO OPERATOR(operate_id,operate_number,person_id,user_name,password,email,phone_number,sign_id,username,pass,admin_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1,operator.getId());
        preparedStatement.setString(2,operator.getOperateNumber());
        preparedStatement.setInt(3,operator.getSignIn().getPerson().getId());
        preparedStatement.setString(4,operator.getSignIn().getPerson().getUsername());
        preparedStatement.setString(5,operator.getSignIn().getPerson().getPassword());
        preparedStatement.setString(6,operator.getSignIn().getPerson().getEmail());
        preparedStatement.setString(7,operator.getSignIn().getPerson().getPhoneNumber());
        preparedStatement.setInt(8,operator.getSignIn().getId());
        preparedStatement.setString(9,operator.getAdmin().getUser());
        preparedStatement.setString(10,operator.getAdmin().getPass());
        preparedStatement.setInt(11,operator.getAdmin().getId());
        return operator;
    }

    @Override
    public Operator edit(Operator operator) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE OPERATOR SET operate_id=?,operate_number=?,person_id=?,user_name=?,password=?,email=?,phone-number=?,sign_id=?,username=?,pass=?,admin_id=? where SIGN_ID=?");
        preparedStatement.setInt(1,operator.getId());
        preparedStatement.setString(2,operator.getOperateNumber());
        preparedStatement.setInt(3,operator.getSignIn().getPerson().getId());
        preparedStatement.setString(4,operator.getSignIn().getPerson().getUsername());
        preparedStatement.setString(5,operator.getSignIn().getPerson().getPassword());
        preparedStatement.setString(6,operator.getSignIn().getPerson().getEmail());
        preparedStatement.setString(7,operator.getSignIn().getPerson().getPhoneNumber());
        preparedStatement.setInt(8,operator.getSignIn().getId());
        preparedStatement.setString(9,operator.getAdmin().getUser());
        preparedStatement.setString(10,operator.getAdmin().getPass());
        preparedStatement.setInt(11,operator.getAdmin().getId());
        return operator;
    }

    @Override
    public Operator remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM OPERATOR WHERE SIGN_ID= ?");                     //TODO : SIGNIN | OPERATOR ?
        preparedStatement.setInt(1, id);                        //TODO
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Operator> findAll() throws Exception {
        List<Operator> operatorList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from operator order by sign_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Operator operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
      //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
            operatorList.add(operator);
        }
        return operatorList;
    }

    @Override
    public Operator findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Sign_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
            return operator;
    }
    public Operator findByPersonId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Person_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByPerUsername(String username) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where User_name LIKE? ORDER BY SIGN_ID");   //TODO
        preparedStatement.setString(1, username + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                   .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .signIn(SignIn.builder().person(Person.builder().username(resultSet.getString("user_name")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByPerPassword(String password) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Password LIKE? ORDER BY SIGN_ID");   //TODO
        preparedStatement.setString(1, password + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .signIn(SignIn.builder().person(Person.builder().password(resultSet.getString("password")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByPerPhone(String phoneNumber) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Phone_Number LIKE? ORDER BY SIGN_ID");   //TODO
        preparedStatement.setString(1, phoneNumber + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .signIn(SignIn.builder().person(Person.builder().phoneNumber(resultSet.getString("Phone_Number")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByPerEmail(String email) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Email LIKE? ORDER BY SIGN_ID");   //TODO
        preparedStatement.setString(1, email+ " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .signIn(SignIn.builder().person(Person.builder().email(resultSet.getString("email")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findBySignId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Sign_id=?");   //TODO
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    //                   .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByAdminId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where Admin_id=?");   //TODO
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    //                   .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    //              .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
        }
        return operator;
    }
    public Operator findByAdminUsername(String user) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where username=? order by admin_id");   //TODO
        preparedStatement.setString(1, user+ " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .admin(Admin.builder().user(resultSet.getString("username")).build())
                    .build();
        }
        return operator;
    }
    public Operator findByAdminPassword(String pass) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Operator where pass=? order by admin_id");   //TODO
        preparedStatement.setString(1, pass+ " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Operator operator = null;
        if (resultSet.next()) {
            operator = Operator
                    .builder()
                    .id(resultSet.getInt("operate_id"))
                    .operateNumber(resultSet.getString("operate_number"))
                    .signIn(SignIn.builder().id(resultSet.getInt("sign_id")).build())
                    .signIn(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .admin(Admin.builder().pass(resultSet.getString("pass")).build())
                    .build();
        }
        return operator;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From Database ");
    }
}
