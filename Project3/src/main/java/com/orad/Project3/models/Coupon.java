package com.orad.Project3.models;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "company_id")
	private Company company;
	private int amount;
	private double price;
	private String title;
	private String description;
	@Enumerated(EnumType.STRING)
	private CategoryTypes category;
	private Date startDate, endDate;
	private String image;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "customers_vs_coupons", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private List<Customer> customers;

	// ctor
	public Coupon() {
	}

	public Coupon(int amount, double price, String title, String description, Date startDate, Date endDate,
			CategoryTypes category, String image) {
		super();
		this.amount = amount;
		this.price = price;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.image = image;
	}

	public Coupon(int id, int amount, double price, String title, String description, Date startDate, Date endDate,
			CategoryTypes category, String image) {
		super();
		this.id = id;
		this.amount = amount;
		this.price = price;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
		this.image = image;
	}

	// get-set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CategoryTypes getCategory() {
		return category;
	}

	public void setCategory(CategoryTypes category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	// methods

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company=" + company + ", amount=" + amount + ", price=" + price + ", title="
				+ title + ", description=" + description + ", category=" + category + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", image=" + image + "]";
	}

	public enum CategoryTypes {
		DontUse, Automotive, Beauty, Food, Health, Hotels, Retail;
	}

}
