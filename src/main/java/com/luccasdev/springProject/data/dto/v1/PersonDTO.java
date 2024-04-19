package com.luccasdev.springProject.data.dto.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;



@JsonPropertyOrder({"Id", "First_Name", "Last_Name", "Address", "Gender"})
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Id")
	@Mapping("id")
	private Long key;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String gender;
	
	public PersonDTO() {
		
	}
	
	public PersonDTO(Long key, String firstName, String lastName, String address, String gender) {
		
		this.key = key;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
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
		PersonDTO other = (PersonDTO) obj;
		return Objects.equals(key, other.key);
	}

	
	
	

	

}
