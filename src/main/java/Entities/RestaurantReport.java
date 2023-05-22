package Entities;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_report")
public class RestaurantReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
    private Integer completedOrdersCount;
    private Integer canceledOrdersCount;
    private double earnings;
    
    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantOwnerID")
    private RestaurantOwner owner;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getCompletedOrdersCount() {
		return completedOrdersCount;
	}

	public void setCompletedOrdersCount(Integer completedOrdersCount) {
		this.completedOrdersCount = completedOrdersCount;
	}

	public int getCanceledOrdersCount() {
		return canceledOrdersCount;
	}

	public void setCanceledOrdersCount(Integer canceledOrdersCount) {
		this.canceledOrdersCount = canceledOrdersCount;
	}

	public double getEarnings() {
		return earnings;
	}

	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RestaurantOwner getOwner() {
		return owner;
	}

	public void setOwner(RestaurantOwner owner) {
		this.owner = owner;
	}
	
}

