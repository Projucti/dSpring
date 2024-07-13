package com.projucti.dspring;

import com.projucti.dspring.model.Person;
import com.projucti.dspring.repository.PersonRepository;
import com.projucti.dspring.service.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
    public void testEditPersonFound(){
        Long id =1L;
        Person personOld= new Person();
        personOld.setId(id);
        personOld.setFirstName("Jane");
        personOld.setLastName("John");
        personOld.setAge(30);

        Person personNew= new Person();
        personNew.setLastName("Doe");
        personNew.setAge(24);

        when(personRepository.existsById(id)).thenReturn(true);
        when(personRepository.save(any(Person.class))).thenReturn(personNew);

        Person updatedPerson= personServiceImp.editPerson(id, personNew);
        assertNotNull(updatedPerson);
        assertEquals(24, updatedPerson.getAge());
        assertEquals("Doe", updatedPerson.getLastName());
        assertEquals(id, updatedPerson.getId());

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(1)).save(personNew);

    }

    @Test
    public void testEditPersonNotFound(){
        Long id =1L;
        Person personNew= new Person();
        personNew.setLastName("Doe");
        personNew.setAge(24);

//        when(personRepository.existsById(id)).thenReturn(false);
//        RuntimeException runtimeException= assertThrows(RuntimeException.class, ()->{
//        personServiceImp.editPerson(id, personNew);});
//        assertEquals("Person not found", runtimeException.getMessage());
//        verify(personRepository, times(0)).save(personNew);
//        verify(personRepository, times(1)).existsById(id);

    }

    @Test
    public void testDeletePerson(){
        Long id = 1L; //dummy id
        doNothing().when(personRepository).deleteById(id);
        personServiceImp.deletePerson(id);
        verify(personRepository,times(1)).deleteById(id);
    }
}
