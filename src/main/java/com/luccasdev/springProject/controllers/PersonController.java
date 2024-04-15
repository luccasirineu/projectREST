package com.luccasdev.springProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luccasdev.springProject.models.Person;
import com.luccasdev.springProject.services.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
	
	@Autowired
	public PersonService personService;

	
	@GetMapping(value = "/{id}")
	public Person findById( @PathVariable(value = "id") String id) throws Exception{
		
		return personService.findById(id);
	}
	
	@GetMapping
	public List<Person> findAll(){
		return personService.findAll();
	}
	
	@PostMapping
	public Person create(@RequestBody Person person){
		return personService.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person){
		return personService.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@RequestBody String id){
		personService.delete(id);
	}
	
	


}
