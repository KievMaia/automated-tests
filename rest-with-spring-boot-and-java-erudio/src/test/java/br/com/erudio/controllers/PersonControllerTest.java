package br.com.erudio.controllers;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServices service;

    private Person person;

    @BeforeEach
    void setup() {
        person = new Person("Kiev", "Maia", "kievmaia@gmail.com", "São José - SC - Brasil", "Male");
    }

    @DisplayName("JUnit test for Given Person Object When Create Person Then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenCreatePerson_ThenReturnSavedPerson() throws Exception {
        //Given
        given(service.create(any(Person.class))).willAnswer((invocation) -> invocation.getArgument(0));

        //When
        ResultActions result = mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(person)));

        //Then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @DisplayName("JUnit test for Given Person Id When Find Person By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindPersonById_ThenPersonObject() throws Exception {
        //Given
        given(service.findById(anyLong())).willReturn(person);

        //When
        ResultActions result = mockMvc.perform(
                get("/person/{id}", 1L));

        //Then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }

    @DisplayName("JUnit test for Given Person Non Exists Id When Find Person By Non Exists Id Then Return Not Found Status")
    @Test
    void testGivenNonExistsPersonId_WhenFindPersonById_ThenReturn404Status() throws Exception {
        //Given
        given(service.findById(anyLong())).willThrow(ResourceNotFoundException.class);

        //When
        ResultActions result = mockMvc.perform(
                get("/person/{id}", 99999L));

        //Then
        result.andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("JUnit test for When All Person Then Return Person List")
    @Test
    void testWhenFindAllPerson_ThenPersonList() throws Exception {
        //Given
        given(service.findAll()).willReturn(List.of(person));

        //When
        ResultActions result = mockMvc.perform(
                get("/person", 1L));

        //Then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(person.getLastName())))
                .andExpect(jsonPath("$[0].email", is(person.getEmail())));
    }

    @DisplayName("JUnit test for Given Person Object When Update Person Then Return Updated Person")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ThenReturnUpdatedPerson() throws Exception {
        //Given
        var updatedPerson = new Person("Kiev", "Maia", "kiev-maia@hotmail.com", "São José - SC - Brasil", "Male");
        given(service.findById(anyLong())).willReturn(person);
        given(service.update(any(Person.class))).willAnswer((invocation -> invocation.getArgument(0)));

        //When
        ResultActions result = mockMvc.perform(
                put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedPerson)));

        //Then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @DisplayName("JUnit test for Given Non Exists Person Object When Update Person Then Return Not Found 404")
    @Test
    void testGivenNonExistsPersonObject_WhenUpdatePerson_ThenReturn404NotFound() throws Exception {
        //Given
        var updatedPerson = new Person("Kiev", "Maia", "kiev-maia@hotmail.com", "São José - SC - Brasil", "Male");
        given(service.findById(9999L)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class))).willAnswer((invocation -> invocation.getArgument(1)));

        //When
        ResultActions result = mockMvc.perform(
                put("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(updatedPerson)));

        //Then
        result.andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("JUnit test for Given Person Id When Delete Person Then Delete Person")
    @Test
    void testGivenPersonId_WhenDeletePerson_ThenDeletePerson() throws Exception {
        //Given
        var personId = 1L;
        willDoNothing().given(service).delete(personId);
        //When
        ResultActions result = mockMvc.perform(
                delete("/person/{id}", personId));

        //Then
        result.andExpect(status().isNoContent())
                .andDo(print());
    }
}