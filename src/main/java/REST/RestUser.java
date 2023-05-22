package REST;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EJB.UserEJB;
import Entities.Runner;
import Entities.User;

import java.util.ArrayList;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestUser {

    @EJB
    private UserEJB userEJB;

    @POST
    @Path("/signup")
    public Response signUp(User user) {
        userEJB.signUp(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Integer userId) {
        User user = userEJB.getUserById(userId);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") Integer userId, Runner updatedUser) {
    	User user = userEJB.getUserById(userId);
        if (user != null) {
            updatedUser.setId(userId);
            userEJB.updateUser(updatedUser);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        User authenticatedUser = userEJB.login(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            return Response.ok(authenticatedUser).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    public Response getAllUsers() {
        ArrayList<User> users = userEJB.getAllUsers();
        return Response.ok(users).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") Integer userId) {
        userEJB.deleteUser(userId);
        return Response.ok().build();
    }
}

