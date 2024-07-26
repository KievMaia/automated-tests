package br.com.kiev.service;

import br.com.kiev.model.Person;

public class PersonService implements IPersonService {

    @Override
    public Person createPerson(Person person) {
        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return person;
    }
}
