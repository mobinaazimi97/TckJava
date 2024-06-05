package tck.controller;

import tck.model.bl.ResponseBl;
import tck.model.entity.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Response")
public class ResponseController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(){
        ResponseBl.getResponseBl().getClass();

}
