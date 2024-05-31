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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping(value = "/api/person/v1")
@Tag(name = "People", description = "Endpoints For Managing People") // tag para indicar sobre oq é esse endpoint no swagger
public class PersonController {
	
	@Autowired
	public PersonService personService;

	
	@GetMapping(value = "/{id}" )
	@Operation(summary = "Finds a Person", description = "Finds a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDTO findById( @PathVariable(value = "id") Long id) throws Exception{
		
		return personService.findById(id);
	}
	
	@GetMapping(value = "/people")
	@Operation(summary = " Finds all people", description = "Finds all people",
	tags = {"People"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = {@Content(mediaType="application-json",array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))}),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public List<PersonDTO> findAll(){
		return personService.findAll();
	}
	
	@PostMapping//(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,"Application/x-yaml"}, 
				//consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,"Application/x-yaml"})
	@Operation(summary = "Create a Person", description = "Create a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDTO create(@RequestBody PersonDTO person) throws Exception{
		return personService.create(person);
	}
	
	@PostMapping(value = "/v2")//, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,"Application/x-yaml"}, 
								//consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,"Application/x-yaml"})
	@Operation(summary = "Create a Person", description = "Create a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDTOv2 createV2(@RequestBody PersonDTOv2 person){
		return personService.createV2(person);
	}
	
	@PutMapping//(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,"Application/x-yaml"}, 
				//consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "Application/x-yaml"})
	@Operation(summary = "Update a person", description = "Update a Person",
	tags = {"People"},
	responses = {
			@ApiResponse(description= "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public PersonDTO update(@RequestBody PersonDTO person) throws Exception{
		return personService.update(person);
	}
	
	@Operation(summary = "Delete a Person", description = "Delete a Person",
			tags = {"People"},
			responses = {
					@ApiResponse(description= "Deleted", responseCode = "204", content = @Content()),
					@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	


}
