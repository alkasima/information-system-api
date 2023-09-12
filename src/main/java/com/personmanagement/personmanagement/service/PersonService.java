package com.personmanagement.personmanagement.service;

import com.personmanagement.personmanagement.entity.PersonEntity;
import com.personmanagement.personmanagement.exception.ResourceException;
import com.personmanagement.personmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<PersonEntity> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public PersonEntity createPerson(PersonEntity person) {
        return personRepository.save(person);
    }

    public PersonEntity updatePerson(Long id, PersonEntity updatedPerson) {
        // Check if the person exists
        if (!personRepository.existsById(id)) {
            throw new ResourceException("Person not found with id: " + id);
        }

        //updatedPerson.setId(id);
        return personRepository.save(updatedPerson);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}