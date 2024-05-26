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

public class ResponesDa implements AutoCloseable, CRUD<Respones> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    public ResponesDa() throws Exception {
        connection = ConnectionProvider.getConnectionProvider().getConnection();

    }
    @Override
    public Respones save(Respones respones)throws Exception{
        respones.setId(ConnectionProvider.getConnectionProvider().getNextId("Respones_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO RESPONES (ID,TICKET_ID,PERSON_ID,DATE_TIME,STATUS,ANSWER) VALUES (RESPONES_SEQ.NEXTVAL,?,?,?,?,?,?) "
        );
        preparedStatement.setInt(1,respones.getId());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(respones.getDateTime()));
        preparedStatement.setString( 3, String.valueOf(respones.getPerson()));
        preparedStatement.setString( 4, String.valueOf(respones.getTicket()));
        preparedStatement.setString( 5, String.valueOf(respones.getStatus()));
        preparedStatement.setString(6,respones.getAnswer());
        preparedStatement.execute();
        return respones;
    }
    @Override
    public Respones edit(Respones respones) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE RESPONES SET ID=?,TICKET_ID=?,PERSON_ID=?,DATE_TIME=?,STATUS=? , ANSWER=? WHERE id=?");
        preparedStatement.setInt(1, respones.getId());
        preparedStatement.setString( 2, String.valueOf(respones.getTicket()));
        preparedStatement.setString( 3, String.valueOf(respones.getPerson()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(respones.getDateTime()));
        preparedStatement.setString(5, String.valueOf(respones.getStatus()));
        preparedStatement.setString(6, respones.getAnswer());
        preparedStatement.execute();
        return respones;
    }
    @Override
    public Respones remove(int id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "DELETE FROM RESPONES WHERE ID = ?"
        );
        preparedStatement.setInt(1 , id);
        preparedStatement.execute();
        return null;
    }
    @Override
    public List<Respones> findAll() throws Exception {
        List<Respones> responesList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select * from RESPONES order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Respones respones = Respones
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
    public Respones findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select * from RESPONES where id=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        Respones respones = null;
        if(resultSet.next()){
            respones = Respones
                    .builder()
                    .id(resultSet.getInt("id"))
                    .person(Person.builder().id(resultSet.getInt("personId")).build())
                    .ticket(Ticket.builder().id(resultSet.getInt("ticketId")).build())
                    .dateTime(resultSet.getTimestamp("Date_Time").toLocalDateTime())
                    .status(Status.valueOf(resultSet.getString("status")))
                    .answer(resultSet.getString("answer"))
                    .build();
        }
        return respones;


    }



        @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }

}
