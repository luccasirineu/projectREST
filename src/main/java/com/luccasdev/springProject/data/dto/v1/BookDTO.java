package com.luccasdev.springProject.data.dto.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;



@JsonPropertyOrder({"Id", "Author", "Launch_date", "Price", "Title"})
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Id")
	@Mapping("id")
	private Long key;
	
	private String author;
	
	private Date launchDate;
	
	private Double price;
	
	private String title;
	
	public BookDTO() {
		
	}

	public BookDTO(Long key, String author, Date launchDate, Double price, String title) {
		super();
		this.key = key;
		this.author = author;
		this.launchDate = launchDate;
		this.price = price;
		this.title = title;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String luthor) {
		this.author = luthor;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLauncHDate(Date launch_date) {
		this.launchDate = launch_date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(key);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(key, other.key);
	}
	
	

	
	
	

	

}
