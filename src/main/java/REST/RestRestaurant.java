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

import EJB.RestaurantEJB;
import Entities.Meal;
import Entities.Restaurant;

@Path("/restaurants")
public class RestRestaurant {

    @EJB
    private RestaurantEJB restaurantEJB;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRestaurant(Restaurant restaurant) {
        restaurantEJB.createRestaurant(restaurant);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{restaurantId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRestaurant(@PathParam("restaurantId") Integer restaurantId, Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantEJB.getRestaurantById(restaurantId);
        if (restaurant != null) {
            updatedRestaurant.setId(restaurantId);
            restaurantEJB.updateRestaurant(updatedRestaurant);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{restaurantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurantById(@PathParam("restaurantId") Integer restaurantId) {
        Restaurant restaurant = restaurantEJB.getRestaurantById(restaurantId);
        if (restaurant != null) {
            return Response.ok(restaurant).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{restaurantId}/menu")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRestaurantMenu(@PathParam("restaurantId") Integer restaurantId, ArrayList<Meal> updatedMenu) {
        try {
            restaurantEJB.updateRestaurantMenu(restaurantId, updatedMenu);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantEJB.getAllRestaurants();
        return Response.ok(restaurants).build();
    }

    @POST
    @Path("/{restaurantId}/reports")
    public Response generateAndPersistRestaurantReport(@PathParam("restaurantId") Integer restaurantId) {
        restaurantEJB.generateAndPersistRestaurantReport(restaurantId);
        return Response.status(Response.Status.CREATED).build();
    }
}

