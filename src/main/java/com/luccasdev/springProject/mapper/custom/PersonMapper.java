package com.luccasdev.springProject.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.luccasdev.springProject.data.dto.v2.PersonDTOv2;
import com.luccasdev.springProject.models.Person;

@Service
public class PersonMapper {

	public PersonDTOv2 convertToDTO(Person person) {
		PersonDTOv2 dto = new PersonDTOv2();
		dto.setId(person.getId());
		dto.setFirstName(person.getFirstName());
		dto.setLastName(person.getLastName());
		dto.setAddress(person.getAddress());
		dto.setGender(person.getGender());
		dto.setBirthday(new Date());
		return dto;
	}

	public Person convertDtoToEntity(PersonDTOv2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		//entity.setBirthday(new Date());
		return entity;
	}

}
