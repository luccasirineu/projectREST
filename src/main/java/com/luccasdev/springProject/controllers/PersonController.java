package com.luccasdev.springProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luccasdev.springProject.data.dto.v1.PersonDTO;
import com.luccasdev.springProject.data.dto.v2.PersonDTOv2;
import com.luccasdev.springProject.services.PersonService;

@RestController
@RequestMapping(value = "/api/person/v1")
public class PersonController {
	
	@Autowired
	public PersonService personService;

	
	@GetMapping(value = "/{id}")
	public PersonDTO findById( @PathVariable(value = "id") Long id) throws Exception{
		
		return personService.findById(id);
	}
	
	@GetMapping
	public List<PersonDTO> findAll(){
		return personService.findAll();
	}
	
	@PostMapping
	public PersonDTO create(@RequestBody PersonDTO person){
		return personService.create(person);
	}
	
	@PostMapping(value = "/v2")
	public PersonDTOv2 createV2(@RequestBody PersonDTOv2 person){
		return personService.createV2(person);
	}
	
	@PutMapping
	public PersonDTO update(@RequestBody PersonDTO person){
		return personService.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	


}
