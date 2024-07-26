package br.com.kiev.tdd;

import br.com.kiev.model.Person;
import br.com.kiev.service.IPersonService;
import br.com.kiev.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    Person person;
    Person actual;
    IPersonService service;

    @BeforeEach
    void beforeEach() {
        //Given /Arrange
        person = new Person(1L, "Kiev", "Maia", "kievmaia@gmail.com", "São José - SC", "Male");
        //When
        service = new PersonService();
        actual = service.createPerson(person);
    }

    @DisplayName("When Create a Person With Success Should Returned a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnPersonObject() {
        assertNotNull(actual, () -> "The createPerson() should not have returned null");
        assertEquals(person.getId(), actual.getId(), () -> "The id is Incorrect");
        assertEquals(person.getName(), actual.getName(), () -> "The first name is Incorrect");
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The last name is Incorrect");
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The email is Incorrect");
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The address is Incorrect");
        assertEquals(person.getGender(), actual.getGender(), () -> "The gender is Incorrect");
    }

    @DisplayName("When Create a Person With Success Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsValidFieldsPersonObject() {
        assertEquals(person.getId(), actual.getId(), () -> "The id is Incorrect");
        assertEquals(person.getName(), actual.getName(), () -> "The first name is Incorrect");
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The last name is Incorrect");
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The email is Incorrect");
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The address is Incorrect");
        assertEquals(person.getGender(), actual.getGender(), () -> "The gender is Incorrect");
    }

    @DisplayName("When Create a Person With Null email Throw Exception")
    @Test
    void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException() {
        person.setEmail(null);
        final var exception = assertThrows(IllegalArgumentException.class,
                () -> service.createPerson(person), "Empty email should have cause an IllegalArgumentException");
        assertEquals(exception.getMessage(), "Email cannot be null or empty",
                () -> "The exception message is incorrect");
    }
}
