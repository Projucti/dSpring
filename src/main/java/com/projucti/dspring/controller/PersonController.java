package com.projucti.dspring.controller;

import com.projucti.dspring.model.Person;
import com.projucti.dspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        personService.addPerson(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{typedId}")
    public Person getPerson(@PathVariable Long typedId) {
        return personService.getPerson(typedId);
    }


    @GetMapping
    public List<Person> getAllPersons(){
        List<Person> allPersons=personService.getAllPersons();
        return allPersons;
    }

//    @GetMapping
//    public ResponseEntity<List<Person>> getAllPerson(
//    @RequestParam(defaultValue = "0") int page,
//    @RequestParam(defaultValue = "10") int size) {
//        List<Person> allPersons= personService.getAllPersons();
//        List<Person> persons = paginate(allPersons, page, size);
//        return new ResponseEntity<>(persons, HttpStatus.OK);
//    }

    // Helper method to paginate a list
//    private List<Person> paginate(List<Person> allPersonslist, int page, int size) {
//        int startIndex = page * size;
//        if (startIndex >= allPersonslist.size()) {
//            return Collections.emptyList(); // Return empty list if page is out of range
//        }
//        int endIndex = Math.min(startIndex + size, allPersonslist.size());
//        return allPersonslist.subList(startIndex, endIndex);
//    }

    @PutMapping("/{typedId}")
    public ResponseEntity<Void> editPerson(@PathVariable Long typedId, @RequestBody Person p){

        try {
            personService.editPerson(typedId, p);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{typedId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long typedId){
        personService.deletePerson(typedId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Person with Id " + typedId + " deleted");
        return ResponseEntity.noContent().headers(headers).build();
    }


}