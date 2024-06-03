package com.luccasdev.springProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.luccasdev.springProject.controllers.PersonController;
import com.luccasdev.springProject.data.dto.v1.PersonDTO;
import com.luccasdev.springProject.data.dto.v2.PersonDTOv2;
import com.luccasdev.springProject.exceptions.ResourceNotFoundException;
import com.luccasdev.springProject.mapper.DozerMapper;
import com.luccasdev.springProject.mapper.custom.PersonMapper;
import com.luccasdev.springProject.models.Person;
import com.luccasdev.springProject.repositories.PersonRepository;

@Service
public class PersonService {
	
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonMapper personMapper;
	
	
	public PersonDTO findById(Long id) throws Exception  {
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		var dto = DozerMapper.parseObject(entity, PersonDTO.class);
		dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return dto ;
	}
	
	public List<PersonDTO> findAll(){
		
		
		var persons = DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class);
		
		persons.stream()
				.forEach(p -> {
					try {
						p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				});
		return persons;
	}
	
	
	public PersonDTO create(PersonDTO person) throws Exception {
		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonDTO dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto ;
		// para salvar no bd o objeto tem que ser padrao mas para retornar ao usuario tem que ser DTO
	}
	
	public PersonDTO update(PersonDTO person) throws Exception {
		Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonDTO dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto ;
	}
	
	public void delete(Long id) {
		
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		personRepository.delete(person);
		
	}
	
	public PersonDTOv2 createV2(PersonDTOv2 person) {
		Person entity = personMapper.convertDtoToEntity(person);
		PersonDTOv2 dto = personMapper.convertToDTO(personRepository.save(entity));
		return dto;
		// para salvar no bd o objeto tem que ser padrao mas para retornar ao usuario tem que ser DTO
	}
}
