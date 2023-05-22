package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.lang.Integer;
import java.util.ArrayList;

@Entity
@Table(name = "runners")
public class Runner extends User {
	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
    private double deliveryFees;
	@Column(nullable = false)
    private Integer tripsCompleted;

	@OneToMany(mappedBy = "runner", cascade = CascadeType.ALL)
    private ArrayList<Order> orders;
	
	public enum RunnerStatus {
	    AVAILABLE,
	    BUSY
	}
	
	@Column(nullable = false)
    private RunnerStatus runnerStatus;
	
	public RunnerStatus getRunnerStatus() {
		return runnerStatus;
	}

	public void setRunnerStatus(RunnerStatus runnerStatus) {
		this.runnerStatus = runnerStatus;
	}

	public double getDeliveryFees() {
		return deliveryFees;
	}

	public void setDeliveryFees(double deliveryFees) {
		this.deliveryFees = deliveryFees;
	}

	public int getTripsCompleted() {
		return tripsCompleted;
	}

	public void setTripsCompleted(Integer tripsCompleted) {
		this.tripsCompleted = tripsCompleted;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	public void removeOrder(Order order) {
		this.orders.remove(order);
	}
	
}
