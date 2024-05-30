package com.projucti.dspring.controller;

import com.projucti.dspring.model.person;
import com.projucti.dspring.service.personservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class personcontroller {
    @Autowired
    private personservice personService;

    @PostMapping("/persons")
    public String createperson(){
//        person p1= new person();
//        p1.setFirstName("Parizaad");
//        p1.setLastName("Musa");
//        p1.setId(2024001L);
//        p1.setAge(1);
//        personService.createperson(p1);
//
//        person p2= new person();
//        p2.setFirstName("Abu Saleh Md");
//        p2.setLastName("Musa");
//        p2.setAge(32);
//        p2.setId(2024002L);
//        personService.createperson(p2);
//
//        person p3= new person();
//        p3.setFirstName("Taslima");
//        p3.setLastName("Akter");
//        p3.setAge(32);
//        p3.setId(2024003);
//        personService.createperson(p3);

        return "person name added";
    }

    @GetMapping("/persons/{typedFirstName}")
    public List<person> getpersonbyfirstname(@PathVariable String typedFirstName){
        return personService.getpersonbyfirstname(typedFirstName);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<person>> getallperson(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        List<person> allPersons= personService.getallperson();
        List<person> persons = paginate(allPersons, page, size);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    // Helper method to paginate a list
    private List<person> paginate(List<person> allPersonslist, int page, int size) {
        int startIndex = page * size;
        if (startIndex >= allPersonslist.size()) {
            return Collections.emptyList(); // Return empty list if page is out of range
        }
        int endIndex = Math.min(startIndex + size, allPersonslist.size());
        return allPersonslist.subList(startIndex, endIndex);
    }

    @PutMapping("/persons/{typedFirstName}")
    public ResponseEntity<Void> editpersonbyfirstname(@PathVariable String typedFirstName, @RequestBody String newFirstName){

        personService.editpersonbyfirstname(typedFirstName,newFirstName);

        return ResponseEntity.noContent().build();
    }




    @DeleteMapping("/persons/{typedFirstName}")
    public ResponseEntity<Void> deletepersonbyfirstname(@PathVariable String typedFirstName){
        personService.deletepersonbyfirstname(typedFirstName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Person with First Name " + typedFirstName + " deleted");
        return ResponseEntity.noContent().headers(headers).build();
    }


}