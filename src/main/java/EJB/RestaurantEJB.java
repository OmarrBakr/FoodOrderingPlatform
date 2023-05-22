package EJB;

import java.lang.Integer;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Meal;
import Entities.Order;
import Entities.Restaurant;
import Entities.RestaurantReport;

@Stateless
public class RestaurantEJB {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public void createRestaurant(Restaurant restaurant) {
        entityManager.persist(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        entityManager.merge(restaurant);
    }

    public Restaurant getRestaurantById(Integer restaurantId) {
        return entityManager.find(Restaurant.class, restaurantId);
    }
    
    public void updateRestaurantMenu(Integer restaurantId, ArrayList<Meal> updatedMenu) throws IllegalArgumentException {
        Restaurant restaurant = entityManager.find(Restaurant.class, restaurantId);
        if (restaurant != null) {
            restaurant.getMeals().clear();
            restaurant.setMeals(updatedMenu);
            entityManager.merge(restaurant);
        } else {
            throw new IllegalArgumentException("No restaurant with ID: " + restaurantId);
        }
    }

    public void generateAndPersistRestaurantReport(Integer restaurantId) {
        TypedQuery<Integer> completedOrdersQuery = entityManager.createQuery("SELECT COUNT(o) FROM Order o WHERE o.restaurant_id = :restaurantId AND o.orderStatus = :status", Integer.class);
        completedOrdersQuery.setParameter("restaurantId", restaurantId);
        completedOrdersQuery.setParameter("status", Order.OrderStatus.DELIVERED);
        Integer completedOrdersCount = completedOrdersQuery.getSingleResult();

        TypedQuery<Integer> canceledOrdersQuery = entityManager.createQuery("SELECT COUNT(o) FROM Order o WHERE o.restaurant_id = :restaurantId AND o.orderStatus = :status", Integer.class);
        canceledOrdersQuery.setParameter("restaurantId", restaurantId);
        canceledOrdersQuery.setParameter("status", Order.OrderStatus.CANCELED);
        Integer canceledOrdersCount = canceledOrdersQuery.getSingleResult();

        TypedQuery<Double> earningsQuery = entityManager.createQuery("SELECT SUM(o.totalPrice) FROM Order o WHERE o.restaurant_id = :restaurantId AND o.orderStatus = :status", Double.class);
        earningsQuery.setParameter("restaurantId", restaurantId);
        earningsQuery.setParameter("status", Order.OrderStatus.DELIVERED);
        Double earnings = earningsQuery.getSingleResult();

        Restaurant restaurant = entityManager.find(Restaurant.class, restaurantId);

        RestaurantReport report = new RestaurantReport();
        report.setRestaurant(restaurant);
        report.setCompletedOrdersCount(completedOrdersCount.intValue());
        report.setCanceledOrdersCount(canceledOrdersCount.intValue());
        report.setEarnings(earnings);

        entityManager.persist(report);
    }
    
    public ArrayList<Restaurant> getAllRestaurants() {
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
        return (ArrayList<Restaurant>) query.getResultList();
    }
    
}


