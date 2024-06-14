package com.projucti.dspring.service;

import com.projucti.dspring.model.Person;
import com.projucti.dspring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService{
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);

    }

    @Override
    public Person editPerson(Long id, Person newPerson) {
        if (personRepository.existsById(id)){
            newPerson.setId(id);
           return personRepository.save(newPerson);
        }
        else{
            throw new RuntimeException("Person not found");
        }

    }


}
