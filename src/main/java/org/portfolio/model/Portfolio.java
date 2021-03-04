package org.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio_info")
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String stockName;
	private int quantity;
	private double avgBuyPrice;
	private double currentPrice;
	private double marketValue;
	private double unrealizedReturns;
	private double realizedReturns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getAvgBuyPrice() {
		return avgBuyPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getUnrealizedReturns() {
		return unrealizedReturns;
	}

	public double getRealizedReturns() {
		return realizedReturns;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setAvgBuyPrice(double avgBuyPrice) {
		this.avgBuyPrice = avgBuyPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public void setUnrealizedReturns(double unrealizedReturns) {
		this.unrealizedReturns = unrealizedReturns;
	}

	public void setRealizedReturns(double realizedReturns) {
		this.realizedReturns = realizedReturns;
	}

}
