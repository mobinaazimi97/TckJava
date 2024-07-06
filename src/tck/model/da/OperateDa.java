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
        return null;
    }

    @Override
    public Operator remove(int id) throws Exception {
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
                    .admin(Admin.builder().id(resultSet.getInt("admin_id")).build())
                    .person(SignIn.builder().person(Person.builder().id(resultSet.getInt("person_id")).build()).build())
                    .build();
            operatorList.add(operator);
        }
        return operatorList;
    }

    @Override
    public Operator findById(int id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From Database ");
    }
}
