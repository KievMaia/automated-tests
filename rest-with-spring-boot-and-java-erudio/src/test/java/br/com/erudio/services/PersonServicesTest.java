package br.com.erudio.services;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    @Mock
    private PersonRepository repository;
    @InjectMocks
    private PersonServices service;

    private Person person;

    @BeforeEach
    void setup() {
        person = new Person("Kiev", "Maia", "kievmaia@gmail.com", "São José - SC - Brasil", "Male");
    }

    @DisplayName("JUnit test for Given Person Save Person Then Return Person")
    @Test
    void testGivenPerson_WhenSavePerson_ThenReturnPerson() {
        //Given
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());
        given(repository.save(person)).willReturn(person);

        //When
        var savedPerson = service.create(person);

        //Then
        assertEquals(person, savedPerson);
        assertEquals(person.getFirstName(), savedPerson.getFirstName());
    }

    @DisplayName("JUnit test for Given Duplicate Person Email When Save Then Return Person")
    @Test
    public void create_whenEmailAlreadyExists_throwsException() {
        given(repository.findByEmail(person.getEmail())).willReturn(Optional.of(person));

        assertThrows(ResourceNotFoundException.class, () -> service.create(person));
    }
}