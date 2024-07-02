package com.projucti.dspring;

import com.projucti.dspring.model.Person;
import com.projucti.dspring.repository.PersonRepository;
import com.projucti.dspring.service.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceImpTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImp personServiceImp;

    @BeforeEach
    public void SetUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPerson(){
        Person person=new Person();
        person.setFirstName("Jane");
        person.setLastName("John");
        person.setAge(30);

        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person addedPerson= personServiceImp.addPerson(person);

        assertNotNull(addedPerson);
        assertEquals(addedPerson.getFirstName(),"Jane");
        assertEquals(addedPerson.getLastName(),"John");
        assertEquals(addedPerson.getAge(),30);

        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testGetPerson(){
        Long id =1L;
        Person person= new Person();
        person.setId(id);
        person.setFirstName("Jane");
        person.setLastName("John");
        person.setAge(30);


    }

    @Test
    public void testDeletePerson(){
        Long id = 1L; //dummy id
        doNothing().when(personRepository).deleteById(id);
        personServiceImp.deletePerson(id);
        verify(personRepository,times(1)).deleteById(id);
    }
}
