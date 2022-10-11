package lemonadestand.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Lemonade implements Serializable, Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4121610615006613106L;
	private Integer id;
	private double lemonJuice;
	private double water;
	private double sugar;
	private double price;
	private Order order;
	private int iceCubes;
	@JsonIgnore
	private final int cup = 1;

	public Lemonade() {
	}

	public Lemonade(double lemonJuice, double water, double sugar, int iceCubes, Order order) {
		super();
		this.lemonJuice = lemonJuice;
		this.water = water;
		this.sugar = sugar;
		this.iceCubes = iceCubes;
		this.order = order;
		price = (lemonJuice * .3) + (sugar * .15) + (cup * .50);
	}

	public Lemonade(Integer id, double lemonJuice, double water, double sugar, int iceCubes, double price,
			Order order) {
		this.id = id;
		this.lemonJuice = lemonJuice;
		this.water = water;
		this.sugar = sugar;
		this.iceCubes = iceCubes;
		this.price = price;
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setLemonJuice(double lemonJuice) {
		this.lemonJuice = lemonJuice;
	}

	public void setWater(double water) {
		this.water = water;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setIceCubes(int iceCubes) {
		this.iceCubes = iceCubes;
	}

	void calculateTotal() {
		price = (lemonJuice * .3) + (sugar * .15) + (cup * .5);
	}

	public double getLemonJuice() {
		return lemonJuice;
	}

	public double getWater() {
		return water;
	}

	public double getSugar() {
		return sugar;
	}

	public double getPrice() {
		return price;
	}

	public int getIceCubes() {
		return iceCubes;
	}

	public Integer getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@JsonIgnore
	public int getCup() {
		return cup;
	}

	@Override
	public String toString() {
		return "Lemonade [lemonJuice=" + lemonJuice + ", water=" + water + ", sugar=" + sugar + ", price=" + price
				+ ", iceCubes=" + iceCubes + ", cup=" + cup + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cup;
		result = prime * result + iceCubes;
		long temp;
		temp = Double.doubleToLongBits(lemonJuice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sugar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(water);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lemonade other = (Lemonade) obj;
		if (cup != other.cup)
			return false;
		if (iceCubes != other.iceCubes)
			return false;
		if (Double.doubleToLongBits(lemonJuice) != Double.doubleToLongBits(other.lemonJuice))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(sugar) != Double.doubleToLongBits(other.sugar))
			return false;
		if (Double.doubleToLongBits(water) != Double.doubleToLongBits(other.water))
			return false;
		return true;
	}

}
