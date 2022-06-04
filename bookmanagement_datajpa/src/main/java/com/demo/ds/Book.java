package com.demo.ds;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
public class Book {

	
	@Id
	@NotEmpty(message = "Book Code must not be empty.")
	private String code;
	@NotEmpty(message = "Book Title must not be empty.")
	private String title;
	@NotEmpty(message = "Book Author must not be empty.")
	private String author;
	@NotNull(message = "Book Price must not be empty.")
	private Double price;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
