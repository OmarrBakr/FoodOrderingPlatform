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
import java.util.ArrayList;

import EJB.OrderEJB;
import Entities.Order;

@Path("/orders")
public class RestOrder {

    @EJB
    private OrderEJB orderEJB;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        orderEJB.createOrder(order);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") Integer orderId, Order updatedOrder) {
        Order order = orderEJB.getOrderById(orderId);
        if (order != null) {
            updatedOrder.setId(orderId);
            orderEJB.updateOrder(updatedOrder);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{orderId}/cancel")
    public Response cancelOrder(@PathParam("orderId") Integer orderId) {
        Order order = orderEJB.getOrderById(orderId);
        if (order != null) {
            orderEJB.cancelOrder(order);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("orderId") Integer orderId) {
        Order order = orderEJB.getOrderById(orderId);
        if (order != null) {
            return Response.ok(order).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/customer/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByCustomerId(@PathParam("customerId") Integer customerId) {
        ArrayList<Order> orders = orderEJB.getOrdersByCustomerId(customerId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByRestaurantId(@PathParam("restaurantId") Integer restaurantId) {
        ArrayList<Order> orders = orderEJB.getOrdersByRestaurantId(restaurantId);
        return Response.ok(orders).build();
    }

    @GET
    @Path("/restaurant/{restaurantId}/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedOrdersByRestaurantId(@PathParam("restaurantId") Integer restaurantId) {
        ArrayList<Order> orders = orderEJB.getCompletedOrdersByRestaurantId(restaurantId);
        return Response.ok(orders).build();
    }
}
