package Entities;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_owners")
public class RestaurantOwner extends User {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private ArrayList<Restaurant> restaurants;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private ArrayList<RestaurantReport> reports;

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	public void addRestaurant(Restaurant restaurant) {
		this.restaurants.add(restaurant);
	}
	
	public void removeRestaurant(Restaurant restaurant) {
		this.restaurants.remove(restaurant);
	}

	public ArrayList<RestaurantReport> getReports() {
		return reports;
	}

	public void setReports(ArrayList<RestaurantReport> reports) {
		this.reports = reports;
	}
	
	public void addReport(RestaurantReport report) {
		this.reports.add(report);
	}
	
	public void removeReport(RestaurantReport report) {
		this.reports.remove(report);
	}
}


