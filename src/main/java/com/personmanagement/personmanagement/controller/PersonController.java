package com.personmanagement.personmanagement.controller;

import com.personmanagement.personmanagement.entity.PersonEntity;
import com.personmanagement.personmanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {
        Optional<PersonEntity> person = personService.getPersonById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity person) {
        PersonEntity createdPerson = personService.createPerson(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody PersonEntity updatedPerson) {
        PersonEntity person = personService.updatePerson(id, updatedPerson);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
