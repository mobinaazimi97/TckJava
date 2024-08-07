package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.Person;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Log4j

public class TicketDa implements AutoCloseable, CRUD<Ticket> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TicketDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("TICKET_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TICKET(TICKET_ID,TICKET_DATE,PERSON_ID,TITLE,TEXT,GROUP_NAME,STATUS,USER_NAME) VALUES (TICKET_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setDate(2, Date.valueOf(ticket.getTicketDate()));
        preparedStatement.setInt(3, ticket.getPerson().getId());
        preparedStatement.setString(4, ticket.getTitle());
        preparedStatement.setString(5, ticket.getText());
        preparedStatement.setString(6, ticket.getGroup().name());
        preparedStatement.setString(7,ticket.getStatus().name());
        preparedStatement.setString(8, ticket.getPerson().getUsername());
    //    preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TICKET SET PERSON_ID=?,TITLE=?,TEXT=?,GROUP_NAME=? , STATUS=?,TICKET_DATE=? ,USER_NAME=? WHERE TICKET_ID=?");

        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setInt(2, ticket.getPerson().getId());
        preparedStatement.setString(3, ticket.getTitle());
        preparedStatement.setString(4, ticket.getText());
        preparedStatement.setString(5, String.valueOf(ticket.getGroup()));
        preparedStatement.setString(6, ticket.getGroup().name());
        preparedStatement.setString(7,ticket.getStatus().name());
        preparedStatement.setString(8, ticket.getPerson().getUsername());
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TICKET WHERE TICKET_ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from TICKET order by person_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .ticketDate(resultSet.getDate("ticket_date").toLocalDate())
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                   .status(Status.valueOf(resultSet.getString("status")))
                    .build();

            ticketList.add(ticket);
        }
        return ticketList;

    }

    @Override
    public Ticket findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where ticket_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                   .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;

    }

    public Ticket findByPersonId(int personId) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where PERSON_ID=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("TicketDate").toLocalDate())
                   .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }

    public Ticket findByStatus(Status status) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where status=?");
        preparedStatement.setString(1, String.valueOf(status));
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }

    public Ticket findByGroup(Group group) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where group_name=?");
        preparedStatement.setString(1, String.valueOf(group));
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }

    public Ticket findByTitle(String title) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where title LIKE? ORDER BY TICKET_ID");
        preparedStatement.setString(1, title + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }

    public Ticket findByText(String text) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where text LIKE ? ORDER BY TICKET_ID");
        preparedStatement.setString(1, text + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }

    public Ticket findByDateRange(LocalDate startDate, LocalDate endDate) throws Exception {            //TODO : Not Found DateTime
        preparedStatement = connection.prepareStatement("select * from TICKET where ticket_date between ? and ? ");
    preparedStatement.setDate(1, Date.valueOf(startDate));
    preparedStatement.setDate(2, Date.valueOf(endDate));
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDate(resultSet.getDate("Ticket_Date").toLocalDate())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }
    public Ticket findByUsername(String username) throws Exception {                        //TODO : Not Found Field For User&Pass
        preparedStatement = connection.prepareStatement("select * from TICKET where User_name LIKE? ORDER BY Ticket_Id");
        preparedStatement.setString(1, username + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("ticket_id"))
                    .person(Person.builder().username(resultSet.getString("user_name")).build())
                    .person(Person.builder().id(resultSet.getInt("user_name")).build())
                    .ticketDate(resultSet.getDate("TicketDate").toLocalDate())
                    .group(Group.valueOf(resultSet.getString("group_name")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;
    }
    @Override
    public void close() throws Exception {
//        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From The DataBase");
    }
}
