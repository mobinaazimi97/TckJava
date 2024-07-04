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

public class AdminDa implements AutoCloseable, CRUD<Admin> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public AdminDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");
    }

    @Override
    public Admin save(Admin admin) throws Exception {
        admin.setId(ConnectionProvider.getConnectionProvider().getNextId("admin_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO Admin(Admin_Id,Username,Pass,Person_Id,Person_Family,Ticket_Id,Response_Id,User_name,Password) VALUES (?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, admin.getId());
        preparedStatement.setString(2, admin.getUser());
        preparedStatement.setString(3, admin.getPass());
        preparedStatement.setInt(4, admin.getPerson().getId());
        preparedStatement.setString(5, admin.getPerson().getFamily());
        preparedStatement.setInt(6, admin.getTicket().getId());
        preparedStatement.setInt(7, admin.getResponse().getId());
        preparedStatement.setString(8,admin.getPerson().getUsername());
        preparedStatement.setString(9,admin.getPerson().getPassword());
        return admin;
    }

    @Override
    public Admin edit(Admin admin) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ADMIN SET USERNAME=?,PASS=?,PERSON_ID=?,PERSON_FAMILY=?, TICKET_ID=?,RESPONSE_ID=? ,User_name=?,Password=? WHERE ADMIN_ID=?");
        preparedStatement.setInt(1, admin.getId());
        preparedStatement.setString(2, admin.getUser());
        preparedStatement.setString(3, admin.getPass());
        preparedStatement.setInt(4, admin.getPerson().getId());
        preparedStatement.setString(5, admin.getPerson().getFamily());
        preparedStatement.setInt(6, admin.getTicket().getId());
        preparedStatement.setInt(7, admin.getResponse().getId());
        preparedStatement.setString(8,admin.getPerson().getUsername());
        preparedStatement.setString(9,admin.getPerson().getPassword());
        return admin;
    }

    @Override
    public Admin remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ADMIN WHERE ADMIN_ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Admin> findAll() throws Exception {
        List<Admin> adminList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from admin order by person_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Admin admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                   .person(Person.builder().password(resultSet.getString("password")).build())
                    .person(Person.builder().family(resultSet.getString("person_family")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
            adminList.add(admin);
        }
        return adminList;
    }

    @Override
    public Admin findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where admin_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }

        return admin;
    }

    public Admin findByUser(String user) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Username=?");
        preparedStatement.setString(1, user);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }
    public Admin findByPass(String pass) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Pass=?");
        preparedStatement.setString(1, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }

    public Admin findByPersonId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Person_Id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }
    public List<Admin> findByPersonFamily(String family) throws Exception {
        List<Admin> adminList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from admin  person_family LIKE ? order by person_id");
        preparedStatement.setString(1, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Admin admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().family(resultSet.getString("person_family")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
            adminList.add(admin);
        }
        return adminList;
    }
    public Admin findByPersonUser(String username) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where User_name LIKE? ORDER BY PERSON_ID");
        preparedStatement.setString(1, username + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
      Admin admin = null;
        if (resultSet.next()) {
            admin=Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
        }
    public Admin findByPersonPass(String password) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Password LIKE? ORDER BY PERSON_ID");
        preparedStatement.setString(1, password + " % ");
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin=Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .person(Person.builder().password(resultSet.getString("password")).build())
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }

        public Admin findByTicketId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Ticket_Id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }

    public Admin findByResponseId(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from Admin where Response_Id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Admin admin = null;
        if (resultSet.next()) {
            admin = Admin
                    .builder()
                    .id(resultSet.getInt("Admin_Id"))
                    .user(resultSet.getString("Username"))
                    .pass(resultSet.getString("Pass"))
                    .person(Person.builder().id(resultSet.getInt("person_id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_id")).build())
                    .response(Response.builder().id(resultSet.getInt("response_id")).build())
                    .build();
        }
        return admin;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From Database ");
    }
}
