package REST;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import EJB.MealEJB;
import Entities.Meal;

@Path("/meals")
public class RestMeal {

    @EJB
    private MealEJB mealEJB;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMeal(Meal meal) {
        mealEJB.createMeal(meal);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{mealId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMeal(@PathParam("mealId") Integer mealId, Meal updatedMeal) {
        Meal meal = mealEJB.getMealById(mealId);
        if (meal != null) {
            updatedMeal.setId(mealId);
            mealEJB.updateMeal(updatedMeal);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{mealId}")
    public Response deleteMeal(@PathParam("mealId") Integer mealId) {
        Meal meal = mealEJB.getMealById(mealId);
        if (meal != null) {
            mealEJB.deleteMeal(meal);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{mealId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMealById(@PathParam("mealId") Integer mealId) {
        Meal meal = mealEJB.getMealById(mealId);
        if (meal != null) {
            return Response.ok(meal).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/restaurant/{restaurantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMealsByRestaurantId(@PathParam("restaurantId") Integer restaurantId) {
        ArrayList<Meal> meals = mealEJB.getMealsByRestaurantId(restaurantId);
        return Response.ok(meals).build();
    }
}

