package me.lindner.fidatodo.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import me.lindner.fidatodo.model.ToDoEntry;
import me.lindner.fidatodo.model.ToDoService;

import java.util.List;
import java.util.UUID;

@Path("/todos")
@Produces("application/json")
@Consumes("application/json")
public class ToDoResource {

    @Inject
    ToDoService service;

    @GET
    public List<ToDoEntry> getAll() {
        return service.getEntries();
    }

    @POST
    public ToDoEntry createEntry(@Valid final CreateToDoRequest request) {
        return service.createEntry(request);
    }

    @PUT
    @Path("{id}")
    public Response setEntry(
        @PathParam("id") final UUID id,
        @Valid final UpdateToDoRequest request
    ) {
        if (service.updateEntry(id, request)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEntry(@PathParam("id") final UUID id) {
        if (service.deleteEntry(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
