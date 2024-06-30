package com.projucti.dspring;

import com.projucti.dspring.repository.PersonRepository;
import com.projucti.dspring.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void SetUp()
    {
        MockitoAnnotations.openMocks(this);
    }
}
