package com.luccasdev.springProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public PersonDTO findById(Long id) {
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		return DozerMapper.parseObject(entity, PersonDTO.class);
	}
	
	public List<PersonDTO> findAll(){
		
		return DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class) ;
	}
	
	
	public PersonDTO create(PersonDTO person) {
		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonDTO dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
		return dto;
		// para salvar no bd o objeto tem que ser padrao mas para retornar ao usuario tem que ser DTO
	}
	
	public PersonDTO update(PersonDTO person) {
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonDTO dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
		return dto;
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
