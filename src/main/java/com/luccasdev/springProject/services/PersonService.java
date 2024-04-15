package com.luccasdev.springProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.luccasdev.springProject.models.Person;

@Service
public class PersonService {
	
	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person(counter.incrementAndGet(), "Luccas", "Costa", "Rua Virginia", "Male");
		return person;
	}
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<>();
		Person person1 = new Person(counter.incrementAndGet(), "Luccas", "Costa", "Rua Virginia", "Male");
		Person person2 = new Person(counter.incrementAndGet(), "Roberto", "Silva", "Rua Virginia", "Male");
		Person person3 = new Person(counter.incrementAndGet(), "Carla", "Lima", "Rua Itapecirica", "Female");
		Person person4 = new Person(counter.incrementAndGet(), "Ramirez", "Souza", "Rua Virginia", "Male");
		Person person5 = new Person(counter.incrementAndGet(), "Ana", "Moreira", "Rua Virginia", "Female");
		
		persons.add(person1);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);
		
		
		return persons ;
	}
	
	
	public Person create(Person person) {
		return person; 
	}
	
	public Person update(Person person) {
		return person; 
	}
	
	public void delete(String id) {
		
		
	}
}
