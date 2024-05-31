package tck.model.bl;

import lombok.Getter;
import tck.controller.exceptions.NoResponseFoundException;
import tck.controller.exceptions.NoTicketFoundException;
import tck.model.da.ResponseDa;
import tck.model.da.TicketDa;
import tck.model.entity.Person;
import tck.model.entity.Response;
import tck.model.entity.Ticket;
import tck.model.tool.CRUD;

import java.util.List;

public class ResponseBl implements CRUD<Response> {
    @Getter
    private static ResponseBl responseBl = new ResponseBl();

    private ResponseBl() {
    }

    @Override
    public Response save(Response response) throws Exception {
        try (ResponseDa responseDa = new ResponseDa()) {
            responseDa.save(response);
            return response;
        }
    }

    @Override
    public Response edit(Response response) throws Exception {
        try (ResponseDa responseDa = new ResponseDa()) {
            if (responseDa.findById(response.getId()) != null) {
                responseDa.edit(response);
                return response;
            } else {
                throw new NoResponseFoundException();
            }
        }
    }

    @Override
    public Response remove(int id) throws Exception {
        try (ResponseDa responseDa = new ResponseDa()) {
            Response response = responseDa.findById(id);
            if (response != null) {
                responseDa.remove(id);
                return response;
            } else {
                throw new NoResponseFoundException();
            }
        }
    }

    @Override
    public List<Response> findAll() throws Exception {
        try (ResponseDa responseDa = new ResponseDa()) {
            List<Response> responseList = responseDa.findAll();
            if (!responseList.isEmpty()) {
                return responseList;
            } else {
                throw new NoResponseFoundException();
            }
        }
    }

    @Override
    public Response findById(int id) throws Exception {
        try (ResponseDa responseDa = new ResponseDa()) {
            Response response = responseDa.findById(id);
            if (response != null) {
                int personId = response.getPerson().getId();
                Person person = PersonBl.getPersonBl().findById(personId);
                response.setPerson(person);
                return response;
            } else {
                throw new NoResponseFoundException();
            }
        }
    }
}
