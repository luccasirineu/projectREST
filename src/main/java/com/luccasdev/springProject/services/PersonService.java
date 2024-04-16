package com.luccasdev.springProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luccasdev.springProject.exceptions.ResourceNotFoundException;
import com.luccasdev.springProject.models.Person;
import com.luccasdev.springProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	
	@Autowired
	PersonRepository personRepository;
	
	
	public Person findById(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
	}
	
	public List<Person> findAll(){
		
		return personRepository.findAll() ;
	}
	
	
	public Person create(Person person) {
		return personRepository.save(person); 
	}
	
	public Person update(Person person) {
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(person); 
	}
	
	public void delete(Long id) {
		
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		personRepository.delete(person);
		
	}
}
