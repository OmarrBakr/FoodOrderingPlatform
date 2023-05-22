package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.lang.Integer;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	public enum OrderStatus {
        PREPARING,
        DELIVERED,
        CANCELED
    }
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@ManyToMany
    @JoinTable(name = "order_meals",joinColumns = @JoinColumn(name = "order_id"),inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private ArrayList<Meal> meals;
    
    @Column(nullable = false)
    private double totalPrice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "runner_id")
    private Runner runner;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Meal> getItems() {
		return meals;
	}

	public void setItems(ArrayList<Meal> items) {
		this.meals = items;
	}
	
	public void addItem(Meal item){
		this.meals.add(item);
	}
	
	public void removeItem(Meal item){
		this.meals.remove(item);
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
}
