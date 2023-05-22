package EJB;

import java.lang.Integer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Entities.Order;
import Entities.Runner;

@Stateless
public class RunnerEJB {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public void createRunner(Runner runner) {
        entityManager.persist(runner);
    }

    public Runner getRunnerById(Integer runnerId) {
        return entityManager.find(Runner.class, runnerId);
    }
    
    public void updateRunner(Runner runner) {
        entityManager.merge(runner);
    }

    public void markOrderAsDelivered(Order order) {
        order.setStatus(Order.OrderStatus.DELIVERED);
        entityManager.merge(order);

        Runner runner = order.getRunner();
        runner.setRunnerStatus(Runner.RunnerStatus.AVAILABLE);
        entityManager.merge(runner);
    }
    
    public void setRunnerStatusToBusy(Runner runner) {
        runner.setRunnerStatus(Runner.RunnerStatus.BUSY);
        entityManager.merge(runner);
    }

    public Integer getNumberOfTripsCompleted(Integer runnerId,Order.OrderStatus status) {
        TypedQuery<Integer> query = entityManager.createQuery("SELECT COUNT(o) FROM Order o WHERE o.runner_id = :runnerId AND o.orderStatus = :status", Integer.class);
        query.setParameter("runnerId", runnerId);
        query.setParameter("status", status);
        Integer count = query.getSingleResult();
        return count;
    }
    

}

