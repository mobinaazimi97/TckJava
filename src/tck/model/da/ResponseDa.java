package tck.model.da;

import lombok.extern.log4j.Log4j;
import tck.model.entity.*;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.time.LocalDate;
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
                "INSERT INTO RESPONSE (RESPONSE_ID,TICKET_ID,PERSON_ID,RESPONSE_DATE,ANSWER) VALUES (RESPONSE_SEQ.NEXTVAL,?,?,?,?,?) "
        );
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setInt(2, response.getTicket().getId());
        preparedStatement.setInt(3, response.getPerson().getId());
        preparedStatement.setDate(4, Date.valueOf(response.getDate()));
        preparedStatement.setString(5, response.getAnswer());
  //      preparedStatement.execute();
        return response;
    }

    @Override
    public Response edit(Response response) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE RESPONSE SET TICKET_ID=?,PERSON_ID=?,RESPONSE_DATE=?,ANSWER=? WHERE response_id=?");
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setInt(2, response.getTicket().getId());
        preparedStatement.setInt(3, response.getPerson().getId());
        preparedStatement.setDate(4, Date.valueOf(response.getDate()));
        preparedStatement.setString(5, response.getAnswer());
  //      preparedStatement.execute();
        return response;
    }

    @Override
    public Response remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM RESPONES WHERE response_id = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Response> findAll() throws Exception {
        List<Response> responesList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from RESPONSE order by response_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Response respones = Response
                    .builder()
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .date(resultSet.getDate("response_date").toLocalDate())
                    .answer(resultSet.getString("answer"))
                    .build();

            responesList.add(respones);
        }
        return responesList;
    }

    @Override
    public Response findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where response_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("person_Id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_Id")).build())
                    .date(resultSet.getDate("response_date").toLocalDate())
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
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .date(resultSet.getDate("response_Date").toLocalDate())
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
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .date(resultSet.getDate("response_Date").toLocalDate())
                    .build();
        }
        return response;
    }

    public Response findByAnswer(String answer) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONSE where answer LIKE? ORDER BY response_id");
        preparedStatement.setString(1, answer + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("person_Id")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticket_Id")).build())
                    .date(resultSet.getDate("response_Date").toLocalDate())
                    .answer(resultSet.getString("answer"))
                    .build();
        }
        return response;
    }

    public Response findByDate(LocalDate date) throws Exception {
        preparedStatement = connection.prepareStatement("select * from response where response_date =?");
        preparedStatement.setDate(1, Date.valueOf(String.valueOf(date)));
        ResultSet resultSet = preparedStatement.executeQuery();
        Response response = null;
        if (resultSet.next()) {
            response = Response
                    .builder()
                    .id(resultSet.getInt("response_id"))
                    .person(Person.builder().id(resultSet.getInt("PERSON_ID")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("TICKET_ID")).build())
                    .answer(resultSet.getString("answer"))
                    .date(resultSet.getDate("response_Date").toLocalDate())
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
