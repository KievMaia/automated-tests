package br.com.erudio.integrationtests.controller;

import br.com.erudio.config.TestConfigs;
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.model.Person;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static Person person;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "John Doe",
                "Doe",
                "johndoe@example.com",
                "123 Street, City, State, Country",
                "Male");
    }

    @Order(1)
    @DisplayName("JUnit integration given Person Object when Create one Person should return a Person Object")
    @Test
    void integrationTest_when_CreateOnePerson_ShouldReturnAPersonObject() throws IOException {
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        var createdPerson = objectMapper.readValue(content, Person.class);
        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());
        assertNotNull(createdPerson.getAddress());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("John Doe", createdPerson.getFirstName());
        assertEquals("Doe", createdPerson.getLastName());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("123 Street, City, State, Country", createdPerson.getAddress());
        assertEquals("johndoe@example.com", createdPerson.getEmail());
    }

    @Order(2)
    @DisplayName("JUnit integration given Person Object when Update Person should return a Person Object Updated")
    @Test
    void integrationTest_when_UpdatePerson_ShouldReturnAPersonObjectUpdated() throws IOException {
        person.setEmail("joe@bol.com.br");
        person.setFirstName("Jhonny");
        var content = given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body().asString();

        var updated = objectMapper.readValue(content, Person.class);

        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertNotNull(updated.getFirstName());
        assertNotNull(updated.getLastName());
        assertNotNull(updated.getGender());
        assertNotNull(updated.getEmail());
        assertNotNull(updated.getAddress());

        assertTrue(updated.getId() > 0);
        assertNotEquals("johndoe@example.com", updated.getEmail());
        assertNotEquals("John Doe", updated.getFirstName());
    }

    @Order(3)
    @DisplayName("JUnit integration given Person Id when Find Person by Id should return a Person Object")
    @Test
    void integrationTest_when_FindPersonById_ShouldReturnAPersonObject() throws IOException {
        var content = given().spec(specification)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var foundPerson = objectMapper.readValue(content, Person.class);

        assertNotNull(content);
        assertNotNull(foundPerson.getId());
        assertNotNull(foundPerson.getFirstName());
        assertNotNull(foundPerson.getLastName());
        assertNotNull(foundPerson.getGender());
        assertNotNull(foundPerson.getEmail());
        assertNotNull(foundPerson.getAddress());

        assertTrue(foundPerson.getId() > 0);
        assertEquals("Jhonny", foundPerson.getFirstName());
        assertEquals("Doe", foundPerson.getLastName());
        assertEquals("Male", foundPerson.getGender());
        assertEquals("123 Street, City, State, Country", foundPerson.getAddress());
        assertEquals("joe@bol.com.br", foundPerson.getEmail());
    }


    @Order(4)
    @DisplayName("JUnit integration given Person when FindAll Person should return a Person List")
    @Test
    void integrationTest_when_FindAll_ShouldReturnAPersonList() throws IOException {
        var anotherPerson = new Person(
                "Kênia",
                "Rosa",
                "keniareginarosa@example.com",
                "São José - Areias - SC - Brasil",
                "Female");

        given().spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(anotherPerson)
                .when()
                .post()
                .then()
                .statusCode(200);

        var content = given().spec(specification)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var people = Arrays.asList(objectMapper.readValue(content, Person[].class));

        assertNotNull(content);
        assertTrue(people.size() > 1);
        assertEquals(people.size(), 2);
    }

    @Order(5)
    @DisplayName("JUnit integration given Person Id when delete a Person should Delete a Person")
    @Test
    void integrationTest_when_DeletePerson_ShouldDeleteAPerson() throws IOException {
        given().spec(specification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }
}
