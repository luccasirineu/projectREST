package com.luccasdev.springProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.luccasdev.springProject.controllers.BookController;
import com.luccasdev.springProject.controllers.PersonController;
import com.luccasdev.springProject.data.dto.v1.BookDTO;

import com.luccasdev.springProject.exceptions.ResourceNotFoundException;
import com.luccasdev.springProject.mapper.DozerMapper;
import com.luccasdev.springProject.mapper.custom.PersonMapper;
import com.luccasdev.springProject.models.Book;
import com.luccasdev.springProject.repositories.BookRepository;

@Service
public class BookService {
	
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	PersonMapper personMapper;
	
	
	public BookDTO findById(Long id) throws Exception  {
		var entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		var dto = DozerMapper.parseObject(entity, BookDTO.class);
		dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return dto ;
	}
	
	public List<BookDTO> findAll(){
		
		
		var books = DozerMapper.parseListObject(bookRepository.findAll(), BookDTO.class);
		
		books.stream()
				.forEach(p -> {
					try {
						p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				});
		return books;
	}
	
	
	public BookDTO create(BookDTO book) throws Exception {
		Book entity = DozerMapper.parseObject(book, Book.class);
		BookDTO dto = DozerMapper.parseObject(bookRepository.save(entity), BookDTO.class);
		dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
		return dto ;
		// para salvar no bd o objeto tem que ser padrao mas para retornar ao usuario tem que ser DTO
	}
	
	public BookDTO update(BookDTO book) throws Exception {
		Book entity = bookRepository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		BookDTO dto = DozerMapper.parseObject(bookRepository.save(entity), BookDTO.class);
		dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
		return dto ;
	}
	
	public void delete(Long id) {
		
		Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		bookRepository.delete(book);
		
	}
	
	
}
