package com.luccasdev.springProject.data.dto.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;




public class PersonDTOv2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String gender;
	
	private Date birthday;
	
	public PersonDTOv2() {
		
	}
	
	public PersonDTOv2(Long id, String firstName, String lastName, String address, String gender) {
		
		this.id = id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTOv2 other = (PersonDTOv2) obj;
		return Objects.equals(id, other.id);
	}
	
	

	

}
