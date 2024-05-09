package com.projucti.dspring.controller;

import com.projucti.dspring.service.personservice;
import com.projucti.dspring.model.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class personcontroller {
    @Autowired
    private personservice personService;

    @PostMapping("/persons")
    public String createperson(){
        person p1= new person();
        p1.setFirstName("Parizaad");
        p1.setLastName("Musa");
        personService.createperson(p1);

        person p2= new person();
        p2.setFirstName("Abu Saleh Md");
        p2.setLastName("Musa");
        personService.createperson(p2);

        person p3= new person();
        p3.setFirstName("Taslima");
        p3.setLastName("Akter");
        personService.createperson(p3);

        return "person name added";
    }

    @GetMapping("/persons/{typedFirstName}")
    public List<person> getpersonbyfirstname(@PathVariable String typedFirstName){
        return personService.getpersonbyfirstname(typedFirstName);
    }

    @GetMapping("/persons")
    public List<person> getallperson(){
        return personService.getallperson();
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