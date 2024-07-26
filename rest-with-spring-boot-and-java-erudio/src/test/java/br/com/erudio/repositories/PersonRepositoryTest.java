package br.com.erudio.repositories;

import br.com.erudio.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    Person person0;
    Person person1;
    Person person2;
    List<Person> personList;
    Person savedPerson;

    @BeforeEach
    void setUp() {
        person0 = new Person("Kiev", "Maia", "kievmaia@gmail.com", "São José - SC - Brasil", "Male");
        person1 = new Person("Kenia", "Rosa", "keniarosa@gmail.com", "São José - SC - Brasil", "Female");
        person2 = new Person("La Toya", "Rosa Maia", "latoya@gmail.com", "São José - SC - Brasil", "Female");
        personList = repository.saveAll(List.of(person0, person1, person2));
        savedPerson = personList.get(0);
    }

    @DisplayName("Junit test for Given Person Object When Save Then Return Saved Person")
    @Test
    void testGivenPerson_WhenSave_ThenReturnSavedPerson() {
        // Given


        // When

        // Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
        assertEquals(person0.getFirstName(), savedPerson.getFirstName());
        assertEquals(person0.getLastName(), savedPerson.getLastName());
        assertEquals(person0.getEmail(), savedPerson.getEmail());
        assertEquals(person0.getAddress(), savedPerson.getAddress());
        assertEquals(person0.getGender(), savedPerson.getGender());
    }

    @DisplayName("Junit test for Given Person List When Find All Then Return Person List")
    @Test
    void testGivenPersonList_WhenFindAll_ThenReturnPersonList() {
        // Given

        // When

        // Then
        var personList = repository.findAll();

        // Assert
        assertNotNull(personList);
        assertEquals(3, personList.size());

        // Assert
        assertNotNull(personList);
        assertEquals(3, personList.size());
    }

    @DisplayName("Junit test for Given Person When Find By Id Then Return a Person Object")
    @Test
    void testGivenPerson_WhenFindById_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findById(person0.getId()).get();


        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Find By Email Then Return a Person Object")
    @Test
    void testGivenPerson_WhenFindByEmail_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findByEmail(person0.getEmail()).get();


        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Updated Then Return a Person Object")
    @Test
    void testGivenPerson_WhenUpdated_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findById(person0.getId()).get();

        var newName = "Kenia";
        foundPerson.setFirstName(newName);

        var updatedPerson = repository.save(foundPerson);


        // Assert
        assertNotNull(updatedPerson);
        assertEquals(updatedPerson.getFirstName(), newName);
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Deleted Then Remove Person")
    @Test
    void testGivenPerson_WhenDeleted_ThenRemovePersonObject() {
        // Given

        // When
        repository.delete(savedPerson);

        var findPerson = repository.findById(person0.getId()).orElse(null);

        // Assert
        assertNull(findPerson);
    }

    @DisplayName("Junit test for Given Person When Find By Custom JPQL Then Return a Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByCustomJPQL_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findByJPQL(person0.getFirstName(), person0.getLastName());

        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Find By Custom JPQL Named Parameters Then Return a Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByCustomJPQLNamedParameters_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findByJPQLNamedParameters(person0.getFirstName(), person0.getLastName());

        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Find By Custom JPQL Native Query Then Return a Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByCustomJPQLNativeQuery_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findByNativeSQL(person0.getFirstName(), person0.getLastName());

        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

    @DisplayName("Junit test for Given Person When Find By Custom JPQL Named Parameters Native Query Then Return a Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByCustomJPQLNamedParametersNativeQuery_ThenReturnPersonObject() {
        // Given

        // When
        var foundPerson = repository.findByNativeSQLwithNamedParameters(person0.getFirstName(), person0.getLastName());

        // Assert
        assertNotNull(foundPerson);
        assertTrue(foundPerson.getId() > 0);
        assertEquals(foundPerson.getId(), person0.getId());
        assertEquals(person0.getFirstName(), foundPerson.getFirstName());
        assertEquals(person0.getLastName(), foundPerson.getLastName());
        assertEquals(person0.getEmail(), foundPerson.getEmail());
        assertEquals(person0.getAddress(), foundPerson.getAddress());
        assertEquals(person0.getGender(), foundPerson.getGender());
    }

}