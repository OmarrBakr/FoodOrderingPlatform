package EJB;

import java.util.ArrayList;
import java.lang.Integer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.User;

@Stateless
public class UserEJB {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public void signUp(User user) {
        entityManager.persist(user);
    }

    public User getUserById(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    public User login(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public ArrayList<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return (ArrayList<User>) query.getResultList();
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public void deleteUser(Integer userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}


