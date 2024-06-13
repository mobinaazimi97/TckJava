package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.*;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Log4j

public class ResponseDa implements AutoCloseable, CRUD<Response> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ResponseDa() throws Exception {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        log.debug("Connected To The DataBase");

    }

    @Override
    public Response save(Response response) throws Exception {
        response.setId(ConnectionProvider.getConnectionProvider().getNextId("Response_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO RESPONSE (RESPONSE_ID,TICKET_ID,PERSON_ID,RESPONSE_DATE_TIME,ANSWER) VALUES (RESPONSE_SEQ.NEXTVAL,?,?,?,?,?) "
        );
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setInt(2, response.getTicket().getId());
        preparedStatement.setInt(3, response.getPerson().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(response.getDateTime()));
        preparedStatement.setString(5, response.getAnswer());
        preparedStatement.execute();
        return response;
    }

    @Override
    public Response edit(Response response) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE RESPONSE SET RESPONSE_ID=?,TICKET_ID=?,PERSON_ID=?,RESPONSE_DATE_TIME=?,ANSWER=? WHERE id=?");
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setInt(2, response.getTicket().getId());
        preparedStatement.setInt(3, response.getPerson().getId());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(response.getDateTime()));
        preparedStatement.setString(5, response.getAnswer());
        preparedStatement.execute();
        return response;
    }

    @Override
    public Response remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM RESPONES WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Response> findAll() throws Exception {
        List<Response> responesList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from RESPONSE order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Response respones = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .dateTime(resultSet.getTimestamp("dateTime").toLocalDateTime())
                    .answer(resultSet.getString("answer"))
                    .build();

            responesList.add(respones);
        }
        return responesList;
    }

    @Override
    public Response findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("personId")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticketId")).build())
                    .dateTime(resultSet.getTimestamp("Date_Time").toLocalDateTime())
                    .answer(resultSet.getString("answer"))
                    .build();
        }
        return response;
    }

    public Response findByPersonId(int personId) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where PERSON_ID=?");
        preparedStatement.setInt(1, personId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .dateTime(resultSet.getTimestamp("response_Date_Time").toLocalDateTime())
                    .build();
        }
        return response;
    }

    public Response findByTicketId(int ticketId) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where TICKET_ID=?");
        preparedStatement.setInt(1, ticketId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .dateTime(resultSet.getTimestamp("response_Date_Time").toLocalDateTime())
                    .build();
        }
        return response;
    }

    public Response findByAnswer(String answer) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where answer LIKE? ORDER BY ID");
        preparedStatement.setString(1, answer + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("personId")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticketId")).build())
                    .dateTime(resultSet.getTimestamp("Date_Time").toLocalDateTime())
                    .answer(resultSet.getString("answer"))
                    .build();
        }
        return response;
    }

    public Response findByDateTime(LocalDateTime dateTime) throws Exception {
        preparedStatement = connection.prepareStatement("select * from response where response_date_time =?");
        preparedStatement.setTimestamp(1, Timestamp.valueOf(dateTime));
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .dateTime(resultSet.getTimestamp("response_Date_Time").toLocalDateTime())
                    .build();
        }
        return response;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
        log.debug("Disconnected From The DataBase");
    }

}
