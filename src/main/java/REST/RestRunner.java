package REST;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJB.RunnerEJB;
import Entities.Order;
import Entities.Runner;

@Path("/runners")
public class RestRunner {

    @EJB
    private RunnerEJB runnerEJB;

    @PUT
    @Path("/{runnerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRunner(@PathParam("runnerId") Integer runnerId, Runner updatedRunner) {
        Runner runner = runnerEJB.getRunnerById(runnerId);
        if (runner != null) {
            updatedRunner.setId(runnerId);
            runnerEJB.updateRunner(updatedRunner);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/deliver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response markOrderAsDelivered(Order order) {
        runnerEJB.markOrderAsDelivered(order);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{runnerId}/status/busy")
    public Response setRunnerStatusToBusy(@PathParam("runnerId") Integer runnerId) {
        Runner runner = runnerEJB.getRunnerById(runnerId);
        if (runner != null) {
            runnerEJB.setRunnerStatusToBusy(runner);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{runnerId}/trips/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNumberOfTripsCompleted(@PathParam("runnerId") Integer runnerId) {
        int tripsCompleted = runnerEJB.getNumberOfTripsCompleted(runnerId, Order.OrderStatus.DELIVERED);
        return Response.ok(tripsCompleted).build();
    }
}

