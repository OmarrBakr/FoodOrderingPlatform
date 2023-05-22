package EJB;

import java.util.ArrayList;
import java.lang.Integer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Order;

@Stateless
public class OrderEJB {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public void createOrder(Order order) {
        entityManager.persist(order);
    }

    public void updateOrder(Order order) {
        entityManager.merge(order);
    }

    public void cancelOrder(Order order) {
        order.setStatus(Order.OrderStatus.CANCELED);
        entityManager.merge(order);
    }

    public Order getOrderById(Integer orderId) {
        return entityManager.find(Order.class, orderId);
    }

    public ArrayList<Order> getOrdersByCustomerId(Integer customerId) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer_id = :customerId", Order.class);
        query.setParameter("customerId", customerId);
        return (ArrayList<Order>) query.getResultList();
    }

    public ArrayList<Order> getOrdersByRestaurantId(Integer restaurantId) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.restaurant_id = :restaurantId", Order.class);
        query.setParameter("restaurantId", restaurantId);
        return (ArrayList<Order>) query.getResultList();
    }

    public ArrayList<Order> getCompletedOrdersByRestaurantId(Integer restaurantId) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.restaurant_id = :restaurantId AND o.status = :status", Order.class);
        query.setParameter("restaurantId", restaurantId);
        query.setParameter("status", Order.OrderStatus.DELIVERED);
        return (ArrayList<Order>) query.getResultList();
    }
}
