package EJB;

import java.util.ArrayList;
import java.lang.Integer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Meal;

@Stateless
public class MealEJB {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public void createMeal(Meal meal) {
        entityManager.persist(meal);
    }

    public void updateMeal(Meal meal) {
        entityManager.merge(meal);
    }

    public void deleteMeal(Meal meal) {
        entityManager.remove(entityManager.merge(meal));
    }

    public Meal getMealById(Integer mealId) {
        return entityManager.find(Meal.class, mealId);
    }

    public ArrayList<Meal> getMealsByRestaurantId(Integer restaurantId) {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT m FROM Meal m WHERE m.restaurant_id = :restaurantId", Meal.class);
        query.setParameter("restaurantId", restaurantId);
        return (ArrayList<Meal>) query.getResultList();
    }

}

