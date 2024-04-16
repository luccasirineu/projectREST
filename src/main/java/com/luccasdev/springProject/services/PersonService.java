package com.luccasdev.springProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luccasdev.springProject.data.dto.v1.PersonDTO;
import com.luccasdev.springProject.exceptions.ResourceNotFoundException;
import com.luccasdev.springProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	
	@Autowired
	PersonRepository personRepository;
	
	
	public PersonDTO findById(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
	public List<PersonDTO> findAll(){
		
		return personRepository.findAll() ;
	}
	
	
	public PersonDTO create(PersonDTO person) {
		return personRepository.save(person); 
	}
	
	public PersonDTO update(PersonDTO person) {
		PersonDTO entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(entity); 
	}
	
	public void delete(Long id) {
		
		PersonDTO person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		personRepository.delete(person);
		
	}
}
