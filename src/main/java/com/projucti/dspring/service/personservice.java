package com.projucti.dspring.service;

import org.springframework.stereotype.Service;
import com.projucti.dspring.model.person;
import java.util.ArrayList;
import java.util.List;

@Service
public class personservice{
    private final List<person> personList= new ArrayList<>();

    //create person
    public String createperson(person Person){
        personList.add(Person);
        return "Person created successfully";
    }
    public List<person> getallperson(){
        return personList;
    }

    //find person by first name
    public List<person> getpersonbyfirstname(String typedFirstName){
        List<person> matchedpersonList= new ArrayList<>();
        for (person p: personList){
            if (p.getFirstName().equalsIgnoreCase(typedFirstName))
            {
                matchedpersonList.add(p);
            }
        }
        return matchedpersonList;
    }

    //delete person by first name

    public List<person> deletepersonbyfirstname(String typedFirstName){
        personList.removeIf(personList-> personList.getFirstName().toLowerCase().contains(typedFirstName.toLowerCase()));
        return personList;

    }


    //edit person by first name

    public List<person> editpersonbyfirstname(String typedFirstName, String newFirstName){
        for (person p: personList){
            if (p.getFirstName().equalsIgnoreCase(typedFirstName))
            {
                p.setFirstName(newFirstName);
            }
        }
        return personList;

    }






}