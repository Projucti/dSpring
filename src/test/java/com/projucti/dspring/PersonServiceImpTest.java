package com.projucti.dspring;

import com.projucti.dspring.model.Person;
import com.projucti.dspring.repository.PersonRepository;
import com.projucti.dspring.service.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        when(personRepository.findById(id)).thenReturn(Optional.of(person)); //isolating behavior

        Person foundPerson= personServiceImp.getPerson(id);

        assertNotNull(foundPerson);
        assertEquals(foundPerson.getFirstName(), person.getFirstName());
        assertEquals(foundPerson.getLastName(), person.getLastName());
        assertEquals(foundPerson.getAge(),person.getAge());
        verify(personRepository,times(1)).findById(id);
    }

    @Test
    public void testGetAllPersons(){
        Long id1 =1L;
        Person personOne= new Person();
        personOne.setId(id1);
        personOne.setFirstName("Jane");
        personOne.setLastName("John");
        personOne.setAge(30);

        Long id2 =2L;
        Person personTwo= new Person();
        personTwo.setId(id2);
        personTwo.setFirstName("Caroline");
        personTwo.setLastName("Channing");
        personTwo.setAge(28);

        List<Person> personList= Arrays.asList(personOne,personTwo);

        when(personRepository.findAll()).thenReturn(personList);

        List<Person> resultList= personServiceImp.getAllPersons();

        assertNotNull(resultList);
        assertEquals(2, resultList.size());

        assertEquals("Jane", resultList.get(0).getFirstName());
        assertEquals("John", resultList.get(0).getLastName());
        assertEquals(30, resultList.get(0).getAge());

        assertEquals("Caroline", resultList.get(1).getFirstName());
        assertEquals("Channing", resultList.get(1).getLastName());
        assertEquals(28, resultList.get(1).getAge());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePerson(){
        Long id = 1L; //dummy id
        doNothing().when(personRepository).deleteById(id);
        personServiceImp.deletePerson(id);
        verify(personRepository,times(1)).deleteById(id);
    }
}
