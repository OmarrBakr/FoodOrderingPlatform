package Entities;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private ArrayList<Order> orders;

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
