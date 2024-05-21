package tck.model.da;

import tck.model.entity.Group;
import tck.model.entity.Person;
import tck.model.entity.Status;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;
import tck.model.tool.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class TicketDa implements AutoCloseable, CRUD<Ticket> {
        private final Connection connection;
        private PreparedStatement preparedStatement;
        public TicketDa()throws Exception{
            connection = ConnectionProvider.getConnectionProvider().getConnection();
        }
        @Override
        public Ticket save(Ticket ticket) throws Exception {
            ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("TICKET_seq"));
            preparedStatement=connection.prepareStatement(
                    "INSERT INTO TICKET(ID,TICKET_DATE_TIME,PERSON_NAME,PERSON_FAMILY,PERSON_ID,TITLE,GROUP,STATUS) VALUES (PERSON14_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,)"
            );
            preparedStatement.setInt(1,ticket.getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(ticket.getTicketDateTime()));
            preparedStatement.setString(3, ticket.getPerson();
            preparedStatement.setString( 3,ticket.getTitle());
            preparedStatement.setString(5,ticket.getGroup();
            preparedStatement.setString(6,ticket.getStatus();
            preparedStatement.execute();
            return ticket;
        }

        @Override
        public Ticket edit(Ticket ticket) throws Exception {
            preparedStatement=connection.prepareStatement(
                    "UPDATE TICKET SET TICKET_DATE_TIME=?,PERSON_NAMAE=?,PERSON_FAMILY=?,PERSON_ID=?,TITLE=?,GROUP=?,STATUS=? WHERE id=?");

            preparedStatement.setInt( 1,ticket.getId());
            preparedStatement.setString( 2, String.valueOf(ticket.getPerson()));
            preparedStatement.setString( 3,ticket.getTitle());
            preparedStatement.setString(   4, String.valueOf(ticket.getGroup()));
            preparedStatement.setString( 5, String.valueOf(ticket.getStatus()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(ticket.getTicketDateTime()));
            preparedStatement.execute();
            return ticket;
        }

        @Override
        public Ticket remove(int id) throws Exception {
            preparedStatement=connection.prepareStatement(
                    "DELETE FROM TICKET WHERE ID = ?"
            );
            preparedStatement.setInt(1 , id);
            preparedStatement.execute();
            return null;
        }

        @Override
        public List<Ticket> findAll() throws Exception {
            List<Ticket>ticketList = new ArrayList<>();
            preparedStatement = connection.prepareStatement("select * from TICKET order by id");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Ticket ticket = Ticket
                        .builder()
                        .id(resultSet.getInt("id"))
                        .ticketDateTime(resultSet.getTimestamp("ticket_date_time").toLocalDateTime())
                        .title(resultSet.getString("title"))
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
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();


        }
        @Override
        public void close()throws  Exception {
            preparedStatement.close();
            connection.close();
            }
    }
