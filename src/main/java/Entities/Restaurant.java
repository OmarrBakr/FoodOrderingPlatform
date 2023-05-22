package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {
  
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
    private String ownerId;
    
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private ArrayList<Meal> meals;
    
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private ArrayList<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantOwnerID")
    private RestaurantOwner owner;
    
    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private RestaurantReport restaurantReport;
    
	public RestaurantReport getRestaurantReport() {
		return restaurantReport;
	}

	public void setRestaurantReport(RestaurantReport restaurantReport) {
		this.restaurantReport = restaurantReport;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
	
	public void removeMeal(Meal meal) {
		this.meals.remove(meal);
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
	
	public RestaurantOwner getOwner() {
		return owner;
	}

	public void setOwner(RestaurantOwner owner) {
		this.owner = owner;
	}
    
}


