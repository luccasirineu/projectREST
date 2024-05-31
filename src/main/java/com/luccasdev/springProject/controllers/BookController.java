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

import com.luccasdev.springProject.data.dto.v1.BookDTO;
import com.luccasdev.springProject.data.dto.v1.PersonDTO;

import com.luccasdev.springProject.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping(value = "/api/book/v1")
@Tag(name = "Book", description = "Endpoints For Managing Book") // tag para indicar sobre oq é esse endpoint no swagger
public class BookController {
	
	@Autowired
	public BookService bookService;

	
	@GetMapping(value = "/{id}" )
	@Operation(summary = "Finds a Book", description = "Finds a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public BookDTO findById( @PathVariable(value = "id") Long id) throws Exception{
		
		return bookService.findById(id);
	}
	
	@GetMapping
	@Operation(summary = " Finds all book", description = "Finds all book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = {@Content(mediaType="application-json",array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))}),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public List<BookDTO> findAll(){
		return bookService.findAll();
	}
	
	@PostMapping//(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,"Application/x-yaml"}, 
				//consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE,"Application/x-yaml"})
	@Operation(summary = "Create a Book", description = "Create a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description= "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public BookDTO create(@RequestBody BookDTO book) throws Exception{
		return bookService.create(book);
	}
	
	
	
	@PutMapping//(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,"Application/x-yaml"}, 
				//consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "Application/x-yaml"})
	@Operation(summary = "Update a book", description = "Update a Book",
	tags = {"Book"},
	responses = {
			@ApiResponse(description= "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
			@ApiResponse(description= "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
	})
	public BookDTO update(@RequestBody BookDTO book) throws Exception{
		return bookService.update(book);
	}
	
	@Operation(summary = "Delete a Book", description = "Delete a Book",
			tags = {"Book"},
			responses = {
					@ApiResponse(description= "Deleted", responseCode = "204", content = @Content()),
					@ApiResponse(description= "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description= "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description= "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description= "Internal Server Error", responseCode = "500", content = @Content)
			})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	


}
