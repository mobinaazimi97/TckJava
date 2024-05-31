package tck.model.da;

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
                "INSERT INTO TICKET(TICKET_ID,TICKET_DATE_TIME,PERSON_ID,PERSON_FAMILY,TITLE,TEXT,GROUP_NAME,STATUS,date_range) VALUES (PERSON_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(ticket.getTicketDateTime()));
        preparedStatement.setInt(3, ticket.getPerson().getId());
        preparedStatement.setString(4, ticket.getPerson().getFamily());
        preparedStatement.setString(5, ticket.getTitle());
        preparedStatement.setString(6, ticket.getText());
        preparedStatement.setString(7, String.valueOf(ticket.getGroup()));
        preparedStatement.setString(8, String.valueOf(ticket.getStatus()));
        //   preparedStatement.setDate(9, LocalDate.of(ticket.getTicketDateTime().get()));            //TODO
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TICKET SET TICKET_ID=?,TICKET_DATE_TIME=?,PERSON_ID=?,PERSON_FAMILY=?,TITLE=?,TEXT=?,GROUP_NAME=?,STATUS=? ,date_range=?WHERE id=?");

        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setInt(2, ticket.getPerson().getId());
        preparedStatement.setString(3, ticket.getTitle());
        preparedStatement.setString(4, ticket.getPerson().getFamily());
        preparedStatement.setString(5, ticket.getText());
        preparedStatement.setString(6, String.valueOf(ticket.getGroup()));
        preparedStatement.setString(7, String.valueOf(ticket.getStatus()));
        preparedStatement.setTimestamp(8, Timestamp.valueOf(ticket.getTicketDateTime()));
//     preparedStatement.setDate(9, LocalDate.of(ticket.getTicketDateTime().get()));            //TODO
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
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDateTime(resultSet.getTimestamp("ticket_date_time").toLocalDateTime())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .status(Status.valueOf(resultSet.getString("status")))
                    //    .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
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
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //     .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
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
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
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
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
                    .build();
        }
        return ticket;
    }

    public Ticket findByGroup(Group group) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where group=?");
        preparedStatement.setString(1, String.valueOf(group));
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
                    .build();
        }
        return ticket;
    }

    public Ticket findByTitle(String title) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where title=?");
        preparedStatement.setString(1, title);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
                    .build();
        }
        return ticket;
    }

    public Ticket findByText(String text) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where text=?");
        preparedStatement.setString(1, text);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
                    .build();
        }
        return ticket;
    }

    public Ticket findByDateRange(LocalDate dateRange) throws Exception {
        preparedStatement = connection.prepareStatement("select * from TICKET where date_range=?");
//    preparedStatement.setTimestamp(1, Timestamp.valueOf(Date.valueOf(LocalDate.)));
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
                    //   .dateRange(resultSet.getDate("date_range").toLocalDate().range(true))          //todo
                    .build();
        }
        return ticket;
    }

    public List<Ticket> findByPersonFamily(String personFamily) throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM TICKET WHERE PERSON_FAMILY=? order by id");
        preparedStatement.setString(1, personFamily);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket= Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .person(Person.builder().family(resultSet.getString("PERSON_FAMILY")).build())
                    .ticketDateTime(resultSet.getTimestamp("TicketDate_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .group(Group.valueOf(resultSet.getString("group")))
                    .title(resultSet.getString("title"))
                    .text(resultSet.getString("text"))
//                .dateRange(resultSet.getTimestamp("date_range").toLocalDateTime())      //  TODO
                    .build();
            ticketList.add(ticket);
        }
        return ticketList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
