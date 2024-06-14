package com.projucti.dspring.service;

import com.projucti.dspring.model.Person;

import java.util.List;


public interface PersonService{
     Person addPerson(Person person);
     List<Person> getAllPersons();
     Person getPerson(Long id);
     void deletePerson(Long id);
     Person editPerson(Long id, Person newPerson);
}