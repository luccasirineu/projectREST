package com.luccasdev.springProject.services;

import java.util.List;
import java.util.logging.Logger;

import com.luccasdev.springProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService {

	private Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " + username + "!");
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
	}
}
