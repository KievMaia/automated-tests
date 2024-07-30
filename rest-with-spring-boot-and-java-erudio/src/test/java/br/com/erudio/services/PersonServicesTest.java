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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

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

    @DisplayName("JUnit test for Create Person When Duplicate Email Then Thorows a Exception")
    @Test
    public void testCreate_whenEmailAlreadyExists_throwsException() {
        //Given
        given(repository.findByEmail(person.getEmail())).willReturn(Optional.of(person));

        //When
        var exception = assertThrows(ResourceNotFoundException.class, () -> service.create(person));

        //Then //Verificar se em nenhuma situação o repository foi chamado.
        verify(repository, never()).save(any(Person.class));

        assertEquals(exception.getMessage(), "Already have person with this email");
    }

    @DisplayName("JUnit test for When Find All Then Return All Person Objects")
    @Test
    public void testGivenPersonList_whenFindAll_ThenReturnAllPersonObjects() {
        //Given
        given(repository.findAll()).willReturn(List.of(person));

        //When
        var personList = service.findAll();

        //Then //Verificar se em nenhuma situação o repository foi chamado.
        assertNotNull(personList);
        assertEquals(1, personList.size());
    }

    @DisplayName("JUnit test for When Find All Person Then Return A Empty List")
    @Test
    public void testGivenPersonList_whenFindAll_ThenReturnAEmptyList() {
        //Given
        given(repository.findAll()).willReturn(Collections.emptyList());

        //When
        var personList = service.findAll();

        //Then //Verificar se em nenhuma situação o repository foi chamado.
        assertEquals(0, personList.size());
        assertNotNull(personList);
        assertTrue(personList.isEmpty());
    }

    @DisplayName("JUnit test for When Find Person By Id Then Return Person Object")
    @Test
    public void testGivenPerson_whenFindById_ThenReturnPersonObject() {
        //Given
        given(repository.findById(person.getId())).willReturn(Optional.of(person));

        //When
        var personObject = service.findById(person.getId());

        //Then //Verificar se em nenhuma situação o repository foi chamado.
        assertNotNull(personObject);
        assertEquals(person.getId(), personObject.getId());
        assertEquals(person.getFirstName(), personObject.getFirstName());
        assertEquals(person.getLastName(), personObject.getLastName());
        assertEquals(person.getEmail(), personObject.getEmail());
        assertEquals(person.getAddress(), personObject.getAddress());
        assertEquals(person.getGender(), personObject.getGender());
    }

    @DisplayName("JUnit test for Given Person Object Update Person Then Return Person Updated")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ThenReturnPersonUpdated() {
        //Given
        given(repository.findById(person.getId())).willReturn(Optional.of(person));
        given(repository.save(person)).willReturn(person);

        //When
        var personFirstName = person.getFirstName();
        person.setFirstName("Kiev Updated");
        var updatedPerson = service.update(person);

        //Then
        assertEquals(person.getId(), updatedPerson.getId());
        assertNotEquals(personFirstName, updatedPerson.getFirstName());
    }

    @DisplayName("JUnit test for Given Person Object Update Person Then Return Person Updated")
    @Test
    void testGivenPersonId_WhenDeletePerson_ThenDeletePerson() {
        //Given
        given(repository.findById(person.getId())).willReturn(Optional.of(person));
        willDoNothing().given(repository).delete(person);

        //When
        service.delete(person.getId());

        //Then
        verify(repository, times(1)).delete(person);
    }
}