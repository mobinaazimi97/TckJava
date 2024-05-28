package tck.model.da;

import tck.model.entity.Person;
import tck.model.entity.enums.Group;
import tck.model.entity.enums.Status;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDa implements AutoCloseable, CRUD<Ticket> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public TicketDa() throws Exception {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("TICKET_seq"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TICKET(TICKET_ID,TICKET_DATE_TIME,PERSON_ID,TITLE,TEXT,GROUP_NAME,STATUS) VALUES (PERSON_SEQ.NEXTVAL,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(ticket.getTicketDateTime()));
        preparedStatement.setString(3, String.valueOf(ticket.getPerson()));
        preparedStatement.setString(4, ticket.getTitle());
        preparedStatement.setString(5, ticket.getText());
        preparedStatement.setString(6, String.valueOf(ticket.getGroup()));
        preparedStatement.setString(7, String.valueOf(ticket.getStatus()));
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TICKET SET TICKET_ID=?,TICKET_DATE_TIME=?,PERSON_ID=?,TITLE=?,TEXT=?,GROUP_NAME=?,STATUS=? WHERE id=?");

        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setString(2, String.valueOf(ticket.getPerson()));
        preparedStatement.setString(3, ticket.getTitle());
        preparedStatement.setString(4, ticket.getText());
        preparedStatement.setString(5, String.valueOf(ticket.getGroup()));
        preparedStatement.setString(6, String.valueOf(ticket.getStatus()));
        preparedStatement.setTimestamp(7, Timestamp.valueOf(ticket.getTicketDateTime()));
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TICKET WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from TICKET order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .ticketDateTime(resultSet.getTimestamp("ticket_date_time").toLocalDateTime())
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .status(Status.valueOf(resultSet.getString("status")))
                    .build();

            ticketList.add(ticket);
        }
        return ticketList;

    }

    @Override
    public Ticket findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("personId")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .build();
        }
        return ticket;

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
