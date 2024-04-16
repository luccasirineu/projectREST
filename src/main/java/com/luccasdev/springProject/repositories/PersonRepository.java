package com.luccasdev.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luccasdev.springProject.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
