package tck.model.da;

import tck.model.entity.*;
import tck.model.entity.enums.Status;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ResponseDa implements AutoCloseable, CRUD<Response> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public ResponseDa() throws Exception {
        connection = ConnectionProvider.getConnectionProvider().getConnection();

    }

    @Override
    public Response save(Response response) throws Exception {
        response.setId(ConnectionProvider.getConnectionProvider().getNextId("Response_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO RESPONSE (RESPONSE_ID,TICKET_ID,PERSON_ID,RESPONSE_DATE_TIME,STATUS,ANSWER) VALUES (RESPONSE_SEQ.NEXTVAL,?,?,?,?,?,?) "
        );
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setString(4, String.valueOf(response.getTicket()));
        preparedStatement.setString(3, String.valueOf(response.getPerson()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(response.getDateTime()));
        preparedStatement.setString(5, String.valueOf(response.getStatus()));
        preparedStatement.setString(6, response.getAnswer());
        preparedStatement.execute();
        return response;
    }

    @Override
    public Response edit(Response response) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE RESPONSE SET RESPONSE_ID=?,TICKET_ID=?,PERSON_ID=?,RESPONSE_DATE_TIME=?,STATUS=? , ANSWER=? WHERE id=?");
        preparedStatement.setInt(1, response.getId());
        preparedStatement.setString(2, String.valueOf(response.getTicket()));
        preparedStatement.setString(3, String.valueOf(response.getPerson()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(response.getDateTime()));
        preparedStatement.setString(5, String.valueOf(response.getStatus()));
        preparedStatement.setString(6, response.getAnswer());
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
                    .person(Person.builder().id(resultSet.getInt("personId")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticketId")).build())
                    .dateTime(resultSet.getTimestamp("dateTime").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
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
                    .status(Status.valueOf(resultSet.getString("status")))
                    .answer(resultSet.getString("answer"))
                    .build();
        }
        return response;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
