package org.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traders_info")
public class Trades {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String stockName;
	private String date;
	private BuySell key;
	private int quantity;
	private double price;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getStockName() {
		return stockName;
	}
	public String getDate() {
		return date;
	}
	public BuySell getKey() {
		return key;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setKey(BuySell key) {
		this.key = key;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((stockName == null) ? 0 : stockName.hashCode());
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
		Trades other = (Trades) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (key != other.key)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (stockName == null) {
			if (other.stockName != null)
				return false;
		} else if (!stockName.equals(other.stockName))
			return false;
		return true;
	}
	
}
